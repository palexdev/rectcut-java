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

/// Represents an area, such as an area of the screen, by its minimum x and y and maximum x and y.
public final class Rect implements Cloneable {
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

    /// Delegates to [RectSide#cut(Rect, double)].
    public Rect cut(RectSide side, double amount) {
        return side.cut(this, amount);
    }

    /// Cut an area at the top of this rect by the given amount, modifies the original area and returns the cut area.
    ///
    /// Before returning, the `minY` coordinate is also offset of +[#vSpacing()].
    public Rect cutTop(double amount) {
        double minY = this.minY;
        this.minY = Math.min(maxY, this.minY + amount);
        Rect area = Rect.of(minX, minY, maxX, this.minY);
        this.minY += vSpacing;
        return area;
    }

    /// Cut an area at the right of this rect by the given amount, modifies the original area and returns the cut area.
    ///
    /// Before returning, the `maxX` coordinate is also offset of -[#hSpacing()].
    public Rect cutRight(double amount) {
        double maxX = this.maxX();
        this.maxX = Math.max(minX, this.maxX - amount);
        Rect area = Rect.of(this.maxX, minY, maxX, maxY);
        this.maxX -= hSpacing;
        return area;
    }

    /// Cut an area at the bottom of this rect by the given amount, modifies the original area and returns the cut area.
    ///
    /// Before returning, the `maxY` coordinate is also offset of -[#vSpacing()].
    public Rect cutBottom(double amount) {
        double maxY = this.maxY;
        this.maxY = Math.max(minY, this.maxY - amount);
        Rect area = Rect.of(minX, this.maxY, maxX, maxY);
        this.maxY -= vSpacing;
        return area;
    }

    /// Cut an area at the left of this rect by the given amount, modifies the original area and returns the cut area.
    ///
    /// Before returning, the `minX` coordinate is also offset of +[#hSpacing()].
    public Rect cutLeft(double amount) {
        double minX = this.minX;
        this.minX = Math.min(maxX, this.minX + amount);
        Rect area = Rect.of(minX, minY, this.minX, maxY);
        this.minX += hSpacing;
        return area;
    }

    // Get

    /// Delegates to [RectSide#get(Rect, double)].
    public Rect get(RectSide side, double amount) {
        return side.get(this, amount);
    }

    /// Cut an area at the top of this rect by the given amount, leaves the original area untouched and returns the cut area.
    ///
    /// Because this method does not modify the original area, the [#vSpacing()] property is ignored.
    public Rect getTop(double amount) {
        double maxY = Math.min(this.maxY, minY + amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    /// Cut an area at the right of this rect by the given amount, leaves the original area untouched and returns the cut area.
    ///
    /// Because this method does not modify the original area, the [#hSpacing()] property is ignored.
    public Rect getRight(double amount) {
        double minX = Math.max(this.minX, maxX - amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    /// Cut an area at the bottom of this rect by the given amount, leaves the original area untouched and returns the cut area.
    ///
    /// Because this method does not modify the original area, the [#vSpacing()] property is ignored.
    public Rect getBottom(double amount) {
        double minY = Math.max(this.minY, maxY - amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    /// Cut an area at the left of this rect by the given amount, leaves the original area untouched and returns the cut area.
    ///
    /// Because this method does not modify the original area, the [#hSpacing()] property is ignored.
    public Rect getLeft(double amount) {
        double maxX = Math.min(this.maxX, minX + amount);
        return Rect.of(minX, minY, maxX, maxY);
    }

    /// This extension is useful when one needs to center a certain section horizontally.
    /// Leaves the original area untouched because cutting in the middle generates three different parts
    /// (left, middle, right), hence why this returns an array.
    public Rect[] middleHCut(double amount) {
        double mMinX = (width() - amount) / 2.0 + minX;
        double mMaxX = mMinX + amount;
        return new Rect[]{
            Rect.of(minX, minY, mMinX, maxY),
            Rect.of(mMinX, minY, mMaxX, maxY),
            Rect.of(mMaxX, minY, maxX, maxY),
        };
    }

    /// This extension is useful when one needs to center a certain section vertically.
    /// Leaves the original area untouched because cutting in the middle generates three different parts
    /// (top, middle, bottom), hence why this returns an array.
    public Rect[] middleVCut(double amount) {
        double mMinY = (height() - amount) / 2.0 + minY;
        double mMaxY = mMinY + amount;
        return new Rect[]{
            Rect.of(minX, minY, maxX, mMinY),
            Rect.of(minX, mMinY, maxX, mMaxY),
            Rect.of(minX, mMaxY, maxX, maxY),
        };
    }

    // Extend/Collapse
    /// Delegates to [RectSide#add(Rect, double)].
    public Rect add(RectSide side, double amount) {
        return side.add(this, amount);
    }

    /// Extends this area at the top by the given amount, returns a new rect, leaving the original untouched.
    ///
    /// A negative value contracts the area.
    public Rect addTop(double amount) {
        return Rect.of(minX, minY - amount, maxX, minY);
    }

    /// Extends this area at the right by the given amount, returns a new rect, leaving the original untouched.
    ///
    /// A negative value contracts the area.
    public Rect addRight(double amount) {
        return Rect.of(maxX, minY, maxX + amount, maxY);
    }

    /// Extends this area at the bottom by the given amount, returns a new rect, leaving the original untouched.
    ///
    /// A negative value contracts the area.
    public Rect addBottom(double amount) {
        return Rect.of(minX, maxY, maxX, maxY + amount);
    }

    /// Extends this area at the left by the given amount, returns a new rect, leaving the original untouched.
    ///
    /// A negative value contracts the area.
    public Rect addLeft(double amount) {
        return Rect.of(minX - amount, minY, minX, maxY);
    }

    /// Extends this area in all directions by the given amount, returns a new rect, leaving the original untouched.
    ///
    /// A negative value contracts the area.
    public Rect extend(double amount) {
        return Rect.of(
            minX - amount,
            minY - amount,
            maxX + amount,
            maxY + amount
        );
    }

    /// Contracts this area in all directions by the given amount, returns a new rect, leaving the original untouched.
    ///
    /// A negative value expands the area.
    public Rect contract(double amount) {
        return Rect.of(
            minX + amount,
            minY + amount,
            maxX - amount,
            maxY - amount
        );
    }

    // Layout
    /// This extension can be used with UI toolkits, by calling their positioning functions given the `minX` and `minY`
    /// coordinates of this area.
    public Rect position(BiConsumer<Double, Double> fn) {
        fn.accept(minX, minY);
        return this;
    }

    /// This extension can be used with UI toolkits, by calling their sizing functions given the `width` and `height`
    /// values of the area, computed respectively by [#width()] and [#height()].
    public Rect resize(BiConsumer<Double, Double> fn) {
        double w = width();
        double h = height();
        fn.accept(w, h);
        return this;
    }

    /// This extension can be used with UI toolkits to size and position elements.
    /// It's basically a combination of [#resize(BiConsumer)] and [#position(BiConsumer)] into a single function.
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
    protected Rect clone() {
        return Rect.of(minX, minY, maxX, maxY);
    }

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

    /// @return the `minX` coordinate of this area
    public double minX() {return minX;}

    /// @return the `minY` coordinate of this area
    public double minY() {return minY;}

    /// @return the `maxX` coordinate of this area
    public double maxX() {return maxX;}

    /// @return the `maxY` coordinate of this area
    public double maxY() {return maxY;}

    /// @return the `width` of this area as the max between 0 and the difference `maxX - minX`
    public double width() {
        return Math.max(0, maxX - minX);
    }

    /// @return the `height` of this area as the max between 0 and the difference `maxY - minY`
    public double height() {
        return Math.max(0, maxY - minY);
    }

    /// Delegates to [#withInsets(double, double, double, double)]
    ///
    /// @throws IllegalArgumentException if the given array's length is not exactly 4
    public Rect withInsets(double[] insets) {
        if (insets.length != 4)
            throw new IllegalArgumentException("Insets array must be exactly of length 4");
        return withInsets(insets[0], insets[1], insets[2], insets[3]);
    }

    /// Delegates to [#withInsets(double, double, double, double)].
    public Rect withInsets(double uniform) {
        return withInsets(uniform, uniform, uniform, uniform);
    }

    /// Modifies this area's coordinates to take into account the given insets.
    public Rect withInsets(double top, double right, double bottom, double left) {
        minX += left;
        minY += top;
        maxX -= right;
        maxY -= bottom;
        return this;
    }

    /// @return the horizontal spacing used for horizontal cuts (not gets!)
    public double hSpacing() {
        return hSpacing;
    }

    /// Sets the horizontal spacing used for horizontal cuts (not gets!).
    public Rect withHSpacing(double hSpacing) {
        this.hSpacing = hSpacing;
        return this;
    }

    /// @return the vertical spacing used for vertical cuts (not gets!)
    public double vSpacing() {
        return vSpacing;
    }

    /// Sets the vertical spacing used for vertical cuts (not gets!).
    public Rect withVSpacing(double vSpacing) {
        this.vSpacing = vSpacing;
        return this;
    }
}
