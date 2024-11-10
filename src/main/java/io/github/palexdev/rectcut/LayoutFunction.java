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

/// A simple functional interface which offers the four basic parameters to lay out a UI element: the x and y coordinates,
/// the width and the height.
@FunctionalInterface
public interface LayoutFunction {
    void apply(double x, double y, double w, double h);
}
