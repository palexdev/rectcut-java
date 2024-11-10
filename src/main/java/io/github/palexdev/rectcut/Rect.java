/*
 * Copyright (C) 2024 Parisi Alessandro - alessandro.parisi406@gmail.com
 * This file is part of rectcut-java (https://github.com/palexdev/rectcut-java)
 *
 * rectcut-java is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * rectcut-java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with rectcut-java. If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.palexdev.rectcut;

import java.util.Objects;
import java.util.function.BiConsumer;

public final class Rect {
    //================================================================================
    // Properties
    //================================================================================
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    private double hSpacing = 0;
    private double vSpacing = 0;

    //================================================================================
    // Constructors
    //================================================================================
    public Rect(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public static Rect of(double minX, double minY, double maxX, double maxY) {
        return new Rect(minX, minY, maxX, maxY);
    }

    //================================================================================
    // Methods
    //================================================================================

    // Cut
    public Rect cut(RectSide side, double amount) {
        return side.cut(this, amount);
    }

    public Rect cutTop(double amount) {
        double minY = this.minY;
        this.minY = Math.min(maxY, this.minY + amount);
        Rect area = Rect.of(minX, minY, maxX, this.minY);
        this.minY += vSpacing;
        return area;
    }

    public Rect cutRight(double amount) {
        double maxX = this.maxX();
        this.maxX = Math.max(minX, this.maxX - amount);
        Rect area = Rect.of(this.maxX, minY, maxX, maxY);
        this.maxX -= hSpacing;
        return area;
    }

    public Rect cutBottom(double amount) {
        double maxY = this.maxY;
        this.maxY = Math.max(minY, this.maxY - amount);
        Rect area = Rect.of(minX, this.maxY, maxX, maxY);
        this.maxY -= vSpacing;
        return area;
    }

    public Rect cutLeft(double amount) {
        double minX = this.minX;
        this.minX = Math.min(maxX, this.minX + amount);
        Rect area = Rect.of(minX, minY, this.minX, maxY);
        this.minX += hSpacing;
        return area;
    }

    // Get
    public Rect get(RectSide side, double amount) {
        return side.get(this, amount);
    }

    public Rect getTop(double amount) {
        double maxY = Math.min(this.maxY, minY + amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    public Rect getRight(double amount) {
        double minX = Math.max(this.minX, maxX - amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    public Rect getBottom(double amount) {
        double minY = Math.max(this.minY, maxY - amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    public Rect getLeft(double amount) {
        double maxX = Math.min(this.maxX, minX + amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    public Rect[] middleHCut(double amount) {
        double mMinX = (width() - amount) / 2.0 + minX;
        double mMaxX = mMinX + amount;
        return new Rect[] {
            Rect.of(minX, minY, mMinX, maxY),
            Rect.of(mMinX, minY, mMaxX, maxY),
            Rect.of(mMaxX, minY, maxX, maxY),
        };
    }

    public Rect[] middleVCut(double amount) {
        double mMinY = (height() - amount) / 2.0 + minY;
        double mMaxY = mMinY + amount;
        return new Rect[] {
            Rect.of(minX, minY, maxX, mMinY),
            Rect.of(minX, mMinY, maxX, mMaxY),
            Rect.of(minX, mMaxY, maxX, maxY),
        };
    }

    // Extend/Collapse
    public Rect add(RectSide side, double amount) {
        return side.add(this, amount);
    }

    public Rect addTop(double amount) {
        return Rect.of(minX, minY - amount, maxX, minY);
    }

    public Rect addRight(double amount) {
        return Rect.of(maxX, minY, maxX + amount, maxY);
    }

    public Rect addBottom(double amount) {
        return Rect.of(minX, maxY, maxX, maxY + amount);
    }

    public Rect addLeft(double amount) {
        return Rect.of(minX - amount, minY, minX, maxY);
    }

    public Rect extend(double amount) {
        return Rect.of(
            minX - amount,
            minY - amount,
            maxX + amount,
            maxY + amount
        );
    }

    public Rect contract(double amount) {
        return Rect.of(
          minX + amount,
          minY + amount,
          maxX - amount,
          maxY - amount
        );
    }

    // Layout
    public Rect position(BiConsumer<Double, Double> fn) {
        fn.accept(minX, minY);
        return this;
    }

    public Rect resize(BiConsumer<Double, Double> fn) {
        double w = width();
        double h = height();
        fn.accept(w, h);
        return this;
    }

    public Rect layout(LayoutFunction fn) {
        double w = width();
        double h = height();
        fn.apply(minX, minY, w, h);
        return this;
    }

    //================================================================================
    // Overridden Methods
    //================================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rect rect = (Rect) o;
        return Double.compare(minX, rect.minX) == 0 &&
               Double.compare(minY, rect.minY) == 0 &&
               Double.compare(maxX, rect.maxX) == 0 &&
               Double.compare(maxY, rect.maxY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, minY, maxX, maxY);
    }

    @Override
    public String toString() {
        return "Rect[" +
               "minX=" + minX + ", " +
               "minY=" + minY + ", " +
               "maxX=" + maxX + ", " +
               "maxY=" + maxY + ']';
    }

    //================================================================================
    // Getters/Setters
    //================================================================================
    public double minX() {return minX;}

    public double minY() {return minY;}

    public double maxX() {return maxX;}

    public double maxY() {return maxY;}

    public double width() {
        return Math.max(0, maxX - minX);
    }

    public double height() {
        return Math.max(0, maxY - minY);
    }

    public Rect withInsets(double[] insets) {
        if (insets.length != 4)
            throw new IllegalArgumentException("Insets array must be exactly of length 4");
        return withInsets(insets[0], insets[1], insets[2], insets[3]);
    }

    public Rect withInsets(double uniform) {
        return withInsets(uniform, uniform, uniform, uniform);
    }

    public Rect withInsets(double top, double right, double bottom, double left) {
        minX += left;
        minY += top;
        maxX -= right;
        maxY -= bottom;
        return this;
    }

    public double hSpacing() {
        return hSpacing;
    }

    public Rect withHSpacing(double hSpacing) {
        this.hSpacing = hSpacing;
        return this;
    }

    public double vSpacing() {
        return vSpacing;
    }

    public Rect withVSpacing(double vSpacing) {
        this.vSpacing = vSpacing;
        return this;
    }
}
