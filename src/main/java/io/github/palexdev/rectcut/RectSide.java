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


public enum RectSide {
    TOP {
        @Override
        public Rect cut(Rect rect, double amount) {
            return rect.cutTop(amount);
        }

        @Override
        public Rect get(Rect rect, double amount) {
            return rect.getTop(amount);
        }

        @Override
        public Rect add(Rect rect, double amount) {
            return rect.addTop(amount);
        }
    },
    RIGHT {
        @Override
        public Rect cut(Rect rect, double amount) {
            return rect.cutRight(amount);
        }

        @Override
        public Rect get(Rect rect, double amount) {
            return rect.getRight(amount);
        }

        @Override
        public Rect add(Rect rect, double amount) {
            return rect.addRight(amount);
        }
    },
    BOTTOM {
        @Override
        public Rect cut(Rect rect, double amount) {
            return rect.cutBottom(amount);
        }

        @Override
        public Rect get(Rect rect, double amount) {
            return rect.getBottom(amount);
        }

        @Override
        public Rect add(Rect rect, double amount) {
            return rect.addBottom(amount);
        }
    },
    LEFT {
        @Override
        public Rect cut(Rect rect, double amount) {
            return rect.cutLeft(amount);
        }

        @Override
        public Rect get(Rect rect, double amount) {
            return rect.getLeft(amount);
        }

        @Override
        public Rect add(Rect rect, double amount) {
            return rect.addLeft(amount);
        }
    },
    ;

    public abstract Rect cut(Rect rect, double amount);

    public abstract Rect get(Rect rect, double amount);

    public abstract Rect add(Rect rect, double amount);
}
