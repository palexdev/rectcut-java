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

package utills;


import javafx.scene.Node;
import org.scenicview.ScenicView;
import org.testfx.api.FxRobot;

public class Utils {

    public static double widthOf(Node node) {
        double min = node.minWidth(-1);
        double pref = node.prefWidth(-1);
        double max = node.maxWidth(-1);
        double a = Math.max(pref, min);
        double b = Math.max(min, max);
        return Math.min(a, b);
    }

    public static double heightOf(Node node) {
        double min = node.minHeight(-1);
        double pref = node.prefHeight(-1);
        double max = node.maxHeight(-1);
        double a = Math.max(pref, min);
        double b = Math.max(min, max);
        return Math.min(a, b);
    }

    public static void debugView(FxRobot robot, Node node) {
        if (robot != null) {
            robot.interact(() -> ScenicView.show(node.getScene()));
            return;
        }
        ScenicView.show(node.getScene());
    }

    public static void debugView(FxRobot robot, Node node, long sleepMillis) {
        debugView(robot, node);
        sleep(sleepMillis);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {}
    }

}
