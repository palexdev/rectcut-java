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

package interactive;


import io.github.palexdev.rectcut.Rect;
import io.github.palexdev.rectcut.RectSide;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import utills.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utills.Utils.*;

@ExtendWith(ApplicationExtension.class)
public class TestSuite {
    private Stage stage;
    private StackPane root;

    @Start
    void start(Stage stage) {
        this.stage = stage;
    }

    @BeforeEach
    void setup() {
        root = setupStage();
    }

    @AfterEach
    void dispose() {
        Platform.runLater(stage::close);
    }

    @Test
    void testCutLeft() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect left = rect.cutLeft(1.0);

        assertEquals(0.0, left.minX());
        assertEquals(1.0, left.maxX());
        assertEquals(rect.minY(), left.minY());
        assertEquals(rect.maxY(), left.maxY());

        assertEquals(1.0, rect.minX());
        assertEquals(10.0, rect.maxX());
        assertEquals(0.0, rect.minY());
        assertEquals(10.0, rect.maxY());
    }

    @Test
    void testCutRight() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect right = rect.cutRight(1.0);

        assertEquals(9.0, right.minX());
        assertEquals(10.0, right.maxX());
        assertEquals(rect.minY(), right.minY());
        assertEquals(rect.maxY(), right.maxY());

        assertEquals(0.0, rect.minX());
        assertEquals(9.0, rect.maxX());
        assertEquals(0.0, rect.minY());
        assertEquals(10.0, rect.maxY());
    }

    @Test
    void testCutTop() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect top = rect.cutTop(1.0);

        assertEquals(0.0, top.minX());
        assertEquals(10.0, top.maxX());
        assertEquals(0.0, top.minY());
        assertEquals(1.0, top.maxY());

        assertEquals(0.0, rect.minX());
        assertEquals(10.0, rect.maxX());
        assertEquals(1.0, rect.minY());
        assertEquals(10.0, rect.maxY());
    }

    @Test
    void testCutBottom() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect bottom = rect.cutBottom(1.0);

        assertEquals(0.0, bottom.minX());
        assertEquals(10.0, bottom.maxX());
        assertEquals(9.0, bottom.minY());
        assertEquals(10.0, bottom.maxY());

        assertEquals(0.0, rect.minX());
        assertEquals(10.0, rect.maxX());
        assertEquals(0.0, rect.minY());
        assertEquals(9.0, rect.maxY());
    }

    @Test
    void testGetLeft() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect left = rect.getLeft(1.0);

        assertEquals(0.0, left.minX());
        assertEquals(1.0, left.maxX());
        assertEquals(0.0, left.minY());
        assertEquals(10.0, left.maxY());
    }

    @Test
    void testGetRight() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect right = rect.getRight(1.0);

        assertEquals(9.0, right.minX());
        assertEquals(10.0, right.maxX());
        assertEquals(0.0, right.minY());
        assertEquals(10.0, right.maxY());
    }

    @Test
    void testGetTop() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect top = rect.getTop(1.0);

        assertEquals(0.0, top.minX());
        assertEquals(10.0, top.maxX());
        assertEquals(0.0, top.minY());
        assertEquals(1.0, top.maxY());
    }

    @Test
    void testGetBottom() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect bottom = rect.getBottom(1.0);

        assertEquals(0.0, bottom.minX());
        assertEquals(10.0, bottom.maxX());
        assertEquals(9.0, bottom.minY());
        assertEquals(10.0, bottom.maxY());
    }

    @Test
    void testAddLeft() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect left = rect.addLeft(1.0);

        assertEquals(-1.0, left.minX());
        assertEquals(0.0, left.maxX());
        assertEquals(0.0, left.minY());
        assertEquals(10.0, left.maxY());
    }

    @Test
    void testAddRight() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect right = rect.addRight(1.0);

        assertEquals(10.0, right.minX());
        assertEquals(11.0, right.maxX());
        assertEquals(0.0, right.minY());
        assertEquals(10.0, right.maxY());
    }

    @Test
    void testAddTop() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect top = rect.addTop(1.0);

        assertEquals(0.0, top.minX());
        assertEquals(10.0, top.maxX());
        assertEquals(-1.0, top.minY());
        assertEquals(0.0, top.maxY());
    }

    @Test
    void testAddBottom() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect bottom = rect.addBottom(1.0);

        assertEquals(0.0, bottom.minX());
        assertEquals(10.0, bottom.maxX());
        assertEquals(10.0, bottom.minY());
        assertEquals(11.0, bottom.maxY());
    }

    @Test
    void testExtend() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect extended = rect.extend(1.0);

        assertEquals(-1.0, extended.minX());
        assertEquals(11.0, extended.maxX());
        assertEquals(-1.0, extended.minY());
        assertEquals(11.0, extended.maxY());
    }

    @Test
    void testContract() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect contract = rect.contract(1.0);

        assertEquals(1.0, contract.minX());
        assertEquals(9.0, contract.maxX());
        assertEquals(1.0, contract.minY());
        assertEquals(9.0, contract.maxY());
    }

    @Test
    public void testRectCut() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect left = rect.cut(RectSide.LEFT, 1.0);

        // Check the left cut rect
        assertEquals(0.0, left.minX());
        assertEquals(0.0, left.minY());
        assertEquals(1.0, left.maxX());
        assertEquals(10.0, left.maxY());

        // Check the updated original rect in RectCut
        assertEquals(1.0, rect.minX());
        assertEquals(0.0, rect.minY());
        assertEquals(10.0, rect.maxX());
        assertEquals(10.0, rect.maxY());
    }

    @Test
    public void testMiddleHCut() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect[] parts = rect.middleHCut(4.0);

        // Left segment
        assertEquals(0.0, parts[0].minX());
        assertEquals(3.0, parts[0].maxX());
        assertEquals(0.0, parts[0].minY());
        assertEquals(10.0, parts[0].maxY());

        // Middle segment
        assertEquals(3.0, parts[1].minX());
        assertEquals(7.0, parts[1].maxX());
        assertEquals(0.0, parts[1].minY());
        assertEquals(10.0, parts[1].maxY());

        // Right segment
        assertEquals(7.0, parts[2].minX());
        assertEquals(10.0, parts[2].maxX());
        assertEquals(0.0, parts[2].minY());
        assertEquals(10.0, parts[2].maxY());
    }

    @Test
    public void testMiddleVCut() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect[] parts = rect.middleVCut(4.0);

        // Bottom segment
        assertEquals(0.0, parts[0].minY());
        assertEquals(3.0, parts[0].maxY());
        assertEquals(0.0, parts[0].minX());
        assertEquals(10.0, parts[0].maxX());

        // Middle segment
        assertEquals(3.0, parts[1].minY());
        assertEquals(7.0, parts[1].maxY());
        assertEquals(0.0, parts[1].minX());
        assertEquals(10.0, parts[1].maxX());

        // Top segment
        assertEquals(7.0, parts[2].minY());
        assertEquals(10.0, parts[2].maxY());
        assertEquals(0.0, parts[2].minX());
        assertEquals(10.0, parts[2].maxX());
    }

    @Test
    void testMiddleHCutWithInsets() {
        Rect rect = Rect.of(0.0, 0.0, 400.0, 40.0).withInsets(8.0, 8.0, 8.0, 8.0);
        double cutAmount = 144.0;
        Rect[] cuts = rect.middleHCut(cutAmount);

        // Left segment
        assertEquals(8.0, cuts[0].minX());
        assertEquals(8.0, cuts[0].minY());
        assertEquals(128.0, cuts[0].maxX()); // (384 - 144) / 2 + 8
        assertEquals(32.0, cuts[0].maxY());

        // Middle segment
        assertEquals(128.0, cuts[1].minX());
        assertEquals(8.0, cuts[1].minY());
        assertEquals(272.0, cuts[1].maxX()); // 128 + 144
        assertEquals(32.0, cuts[1].maxY());

        // Right segment
        assertEquals(272.0, cuts[2].minX());
        assertEquals(8.0, cuts[2].minY());
        assertEquals(392.0, cuts[2].maxX());
        assertEquals(32.0, cuts[2].maxY());
    }

    @Test
    void testMiddleVCutWithInsets() {
        Rect rect = Rect.of(0.0, 0.0, 40.0, 400.0).withInsets(8.0, 8.0, 8.0, 8.0);
        double cutAmount = 144.0;
        Rect[] cuts = rect.middleVCut(cutAmount);

        // Top segment
        assertEquals(8.0, cuts[0].minX());
        assertEquals(8.0, cuts[0].minY());
        assertEquals(32.0, cuts[0].maxX());
        assertEquals(128.0, cuts[0].maxY()); // (384 - 144) / 2 + 8

        // Middle segment
        assertEquals(8.0, cuts[1].minX());
        assertEquals(128.0, cuts[1].minY());
        assertEquals(32.0, cuts[1].maxX());
        assertEquals(272.0, cuts[1].maxY()); // 128 + 144

        // Bottom segment
        assertEquals(8.0, cuts[2].minX());
        assertEquals(272.0, cuts[2].minY());
        assertEquals(32.0, cuts[2].maxX());
        assertEquals(392.0, cuts[2].maxY());
    }

    @Test
    public void testHSpacing() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0)
            .withHSpacing(1.5);
        assertEquals(1.5, rect.hSpacing());
    }

    @Test
    public void testVSpacing() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0)
            .withVSpacing(2.5);
        assertEquals(2.5, rect.vSpacing());
    }

    @Test
    public void testWithHSpacing() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect updatedRect = rect.withHSpacing(2.0);

        // Ensure hSpacing was updated correctly
        assertEquals(2.0, updatedRect.hSpacing());

        // Verify that other properties remain the same
        assertEquals(rect.minX(), updatedRect.minX());
        assertEquals(rect.maxX(), updatedRect.maxX());
        assertEquals(rect.minY(), updatedRect.minY());
        assertEquals(rect.maxY(), updatedRect.maxY());
    }

    @Test
    public void testWithVSpacing() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 10.0);
        Rect updatedRect = rect.withVSpacing(3.0);

        // Ensure vSpacing was updated correctly
        assertEquals(3.0, updatedRect.vSpacing());

        // Verify that other properties remain the same
        assertEquals(rect.minX(), updatedRect.minX());
        assertEquals(rect.maxX(), updatedRect.maxX());
        assertEquals(rect.minY(), updatedRect.minY());
        assertEquals(rect.maxY(), updatedRect.maxY());
    }

    @Test
    void testCuts() {
        Rect area = Rect.of(0, 0, 400, 400);

        Rect top = area.cutTop(40);
        assertEquals(Rect.of(0, 40, 400, 400), area);
        assertEquals(Rect.of(0, 0, 400, 40), top);
        Rect topLeft = top.cutLeft(40);
        assertEquals(Rect.of(40, 0, 400, 40), top);
        assertEquals(Rect.of(0, 0, 40, 40), topLeft);
        Rect topRight = top.cutRight(40);
        assertEquals(Rect.of(40, 0, 360, 40), top);
        assertEquals(Rect.of(360, 0, 400, 40), topRight);

        Rect bottom = area.cutBottom(40);
        assertEquals(Rect.of(0, 40, 400, 360), area);
        assertEquals(Rect.of(0, 360, 400, 400), bottom);
        Rect bottomRight = bottom.cutRight(40);
        assertEquals(Rect.of(0, 360, 360, 400), bottom);
        assertEquals(Rect.of(360, 360, 400, 400), bottomRight);
        Rect bottomLeft = bottom.cutLeft(40);
        assertEquals(Rect.of(40, 360, 360, 400), bottom);
        assertEquals(Rect.of(0, 360, 40, 400), bottomLeft);
    }

    @Test
    void testToolbar(FxRobot robot) {
        robot.interact(stage::show);
        Region r1 = createRectangle(1);
        Region r2 = createRectangle(2);
        Region r3 = createRectangle(3);
        Region r4 = createRectangle(4);
        Region r5 = createRectangle(5);

        Pane toolbar = new Pane(r1, r2, r3, r4, r5) {
            @Override
            protected double computePrefHeight(double width) {
                double max = getManagedChildren().stream()
                    .mapToDouble(Utils::heightOf)
                    .max()
                    .orElse(0.0);
                return snappedTopInset() + max + snappedBottomInset();
            }

            @Override
            protected void layoutChildren() {
                double w = getWidth();
                double h = getHeight();
                double[] insets = new double[]{
                    snappedTopInset(),
                    snappedRightInset(),
                    snappedBottomInset(),
                    snappedLeftInset()
                };
                Rect area = Rect.of(0, 0, w, h).withInsets(insets);

                // 1
                area.cutLeft(widthOf(r1)).layout(r1::resizeRelocate);

                // 2
                area.cutLeft(widthOf(r2)).layout(r2::resizeRelocate);

                // 3
                area.cutLeft(widthOf(r3)).layout(r3::resizeRelocate);

                // 4
                area.cutRight(widthOf(r4)).layout(r4::resizeRelocate);

                // 5
                area.cutRight(widthOf(r5)).layout(r5::resizeRelocate);
            }
        };
        toolbar.setStyle("-fx-border-color: gray");
        toolbar.setMaxHeight(Region.USE_PREF_SIZE);
        robot.interact(() -> root.getChildren().add(toolbar));

        /*
         * The expected values are determined by implementing the same example with an HBox container
         *
         * Because the toolbar has a border of 1px on all its sides, the coordinates are offset by 1 too
         */

        assertEquals(400.0, toolbar.getWidth());
        assertEquals(42.0, toolbar.getHeight()); // +2 because of borders
        // 1
        assertEquals(1.0, r1.getLayoutX());
        assertEquals(1.0, r1.getLayoutY());
        // 2
        assertEquals(41.0, r2.getLayoutX());
        assertEquals(1.0, r2.getLayoutY());
        // 3
        assertEquals(81.0, r3.getLayoutX());
        assertEquals(1.0, r3.getLayoutY());
        // 4
        assertEquals(359.0, r4.getLayoutX());
        assertEquals(1.0, r4.getLayoutY());
        // 5
        assertEquals(319.0, r5.getLayoutX());
        assertEquals(1.0, r5.getLayoutY());
    }

    @Test
    void testTwoPanelApplication(FxRobot robot) {
        robot.interact(stage::show);

        /*
         * There are several ways to do this. In theory, the proper way would be to have the buttons be children of the
         * top bar. However, for simplicity, we are simply going to overlay them on the bar.
         */

        // Top bar
        Region topBar = createRectangle(-1);
        Region btn1 = createRectangle(1);
        Region btn2 = createRectangle(2);
        Region btn3 = createRectangle(3);

        // Bottom bar
        Region bottomBar = createRectangle(-1);

        // Sides
        Region left = createRectangle(-1);
        Region right = createRectangle(-1);

        // Container
        Pane container = new Pane(topBar, btn1, btn2, btn3, bottomBar, left, right) {
            @Override
            protected double computePrefHeight(double width) {
                double topH = heightOf(topBar);
                double sidesH = Math.max(heightOf(left), heightOf(right));
                double botH = heightOf(bottomBar);
                return snappedTopInset() + topH + sidesH + botH + snappedBottomInset();
            }

            @Override
            protected void layoutChildren() {
                double w = getWidth();
                double h = getHeight();
                double[] insets = new double[]{
                    snappedTopInset(),
                    snappedRightInset(),
                    snappedBottomInset(),
                    snappedLeftInset()
                };
                Rect area = Rect.of(0, 0, w, h).withInsets(insets);

                // Tob bar
                Rect topArea = area.cutTop(heightOf(topBar)).layout(topBar::resizeRelocate);
                topArea.cutRight(widthOf(btn3)).layout(btn3::resizeRelocate);
                topArea.cutRight(widthOf(btn2)).layout(btn2::resizeRelocate);
                topArea.cutRight(widthOf(btn1)).layout(btn1::resizeRelocate);

                // Bottom bar
                area.cutBottom(heightOf(bottomBar)).layout(bottomBar::resizeRelocate);

                // Sides
                area.cutLeft(area.width() / 2.0).layout(left::resizeRelocate);
                area.layout(right::resizeRelocate); // The remaining area is exactly the space of 'right'
            }
        };
        container.setPrefHeight(400.0);
        container.setMaxHeight(Region.USE_PREF_SIZE);
        robot.interact(() -> root.getChildren().add(container));

        // Top bar
        assertEquals(0.0, topBar.getLayoutX());
        assertEquals(0.0, topBar.getLayoutY());
        assertEquals(400.0, topBar.getWidth());
        assertEquals(40.0, topBar.getHeight());

        assertEquals(280.0, btn1.getLayoutX());
        assertEquals(0.0, btn1.getLayoutY());
        assertEquals(40.0, btn1.getWidth());
        assertEquals(40.0, btn1.getHeight());

        assertEquals(320.0, btn2.getLayoutX());
        assertEquals(0.0, btn2.getLayoutY());
        assertEquals(40.0, btn2.getWidth());
        assertEquals(40.0, btn2.getHeight());

        assertEquals(360.0, btn3.getLayoutX());
        assertEquals(0.0, btn3.getLayoutY());
        assertEquals(40.0, btn3.getWidth());
        assertEquals(40.0, btn3.getHeight());

        // Bottom bar
        assertEquals(0.0, bottomBar.getLayoutX());
        assertEquals(360.0, bottomBar.getLayoutY());
        assertEquals(400.0, bottomBar.getWidth());
        assertEquals(40.0, bottomBar.getHeight());

        // Sides
        assertEquals(0.0, left.getLayoutX());
        assertEquals(40.0, left.getLayoutY());
        assertEquals(200.0, left.getWidth());
        assertEquals(320.0, left.getHeight());

        assertEquals(200.0, right.getLayoutX());
        assertEquals(40.0, right.getLayoutY());
        assertEquals(200.0, right.getWidth());
        assertEquals(320.0, right.getHeight());
    }

    @Test
    void testComplexCard(FxRobot robot) {
        root.setPadding(new Insets(8));
        robot.interact(stage::show);

        /*
         * This is what made me port this layout strategy to Java.
         *
         * I needed to quickly and easily implement a card component for my project, and it turned out that both manual
         * and automatic layouts were too complicated.
         *
         * This test effectively proves how powerful and fairly easy to use is the RectCut layout strategy.
         *
         * The card contains vertically: an image, a header and a sub header.
         * Horizontally at the bottom there are the buttons for the actions, vertically centered and evenly spaced out.
         */

        double spacing = 12.0;
        ImageView image = new ImageView(TestSuite.class.getClassLoader().getResource("placeholder.png").toExternalForm());
        image.setPreserveRatio(true);
        Rectangle clip = new Rectangle();
        clip.setTranslateX(1.0);
        clip.widthProperty().bind(image.fitWidthProperty().subtract(2.0));
        clip.heightProperty().bind(image.layoutBoundsProperty().map(Bounds::getHeight));
        clip.setArcWidth(24.0);
        clip.setArcHeight(24.0);
        image.setClip(clip);
        StackPane iContainer = new StackPane(image); // to apply borders to the image
        image.fitWidthProperty().bind(iContainer.widthProperty());
        Text header = new Text("Untitled");
        header.getStyleClass().add("header");
        Text subHeader = new Text("Modified a few seconds ago");
        subHeader.getStyleClass().add("sub-header");
        Region action1 = createRectangle(1);
        Region action2 = createRectangle(2);
        Region action3 = createRectangle(3);

        Pane card = new Pane(iContainer, header, subHeader, action1, action2, action3) {
            @Override
            protected double computePrefWidth(double height) {
                double iWidth = snapSizeX(widthOf(iContainer));
                double textW = snapSizeX(Math.max(widthOf(header), widthOf(subHeader)));
                double actsW = snapSizeX(widthOf(action1) + widthOf(action2) + widthOf(action3));
                return snappedLeftInset() + Math.max(Math.max(iWidth, textW), actsW) + snappedRightInset();
            }

            @Override
            protected double computePrefHeight(double width) {
                double iHeight = snapSizeY(heightOf(iContainer));
                double headH = snapSizeY(heightOf(header));
                double subHeadH = snapSizeY(heightOf(subHeader));
                double actsH = snapSizeY(Math.max(Math.max(heightOf(action1), heightOf(action2)), heightOf(action3)));
                return snappedTopInset() + iHeight + spacing + headH + spacing + subHeadH + spacing + actsH + snappedBottomInset();
            }

            @Override
            protected void layoutChildren() {
                double w = getWidth();
                double h = getHeight();
                double[] insets = new double[]{
                    snappedTopInset(),
                    snappedRightInset(),
                    snappedBottomInset(),
                    snappedLeftInset()
                };
                Rect area = Rect.of(0, 0, w, h)
                    .withInsets(insets)
                    .withVSpacing(spacing);

                // Image
                area.cutTop(snapSizeY(heightOf(iContainer)))
                    .layout(iContainer::resizeRelocate);

                // Texts
                area.cutTop(snapSizeY(heightOf(header)))
                    .layout(header::resizeRelocate);
                area.cutTop(snapSizeY(heightOf(subHeader)))
                    .layout(subHeader::resizeRelocate);

                // Actions
                double a1W = widthOf(action1);
                double a2W = widthOf(action2);
                double a3W = widthOf(action3);
                Rect[] regions = area.middleHCut(a1W + a2W + a3W + spacing * 2);
                assertEquals(112.0, regions[0].width());
                assertEquals(112.0, regions[2].width());
                Rect middle = regions[1].withHSpacing(spacing);
                middle.cutLeft(a1W).layout(action1::resizeRelocate);
                middle.cutLeft(a2W).layout(action2::resizeRelocate);
                middle.cutLeft(a3W).layout(action3::resizeRelocate);
            }
        };
        card.setMaxHeight(Region.USE_PREF_SIZE);
        card.getStyleClass().add("card");
        card.getStylesheets().add(TestSuite.class.getClassLoader().getResource("Card.css").toExternalForm());
        robot.interact(() -> root.getChildren().add(card));

        // Keep in mind the root has uniform padding of 8px

        // Image
        assertEquals(8.0, iContainer.getLayoutX());
        assertEquals(8.0, iContainer.getLayoutY());
        assertEquals(368.0, iContainer.getWidth());
        assertEquals(210.0, iContainer.getHeight());

        // Text
        assertEquals(8.0, header.getLayoutX());
        assertEquals(230.0, header.getBoundsInParent().getMinY());

        assertEquals(8.0, subHeader.getLayoutX());
        assertEquals(261.0, subHeader.getBoundsInParent().getMinY());

        // Actions
        assertEquals(120.0, action1.getLayoutX());
        assertEquals(289.0, action1.getLayoutY());
        assertEquals(40.0, action1.getWidth());
        assertEquals(40.0, action1.getHeight());

        assertEquals(172.0, action2.getLayoutX());
        assertEquals(289.0, action2.getLayoutY());
        assertEquals(40.0, action2.getWidth());
        assertEquals(40.0, action2.getHeight());

        assertEquals(224.0, action3.getLayoutX());
        assertEquals(289.0, action3.getLayoutY());
        assertEquals(40.0, action3.getWidth());
        assertEquals(40.0, action3.getHeight());
    }

    StackPane setupStage() {
        StackPane sp = new StackPane();
        try {
            Scene scene = new Scene(sp, 400, 400);
            FxToolkit.setupStage(s -> s.setScene(scene));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return sp;
    }

    Region createRectangle(int number) {
        StackPane r = new StackPane();
        if (number >= 0) {
            Text text = new Text(String.valueOf(number));
            text.setFont(Font.font(24));
            r.getChildren().add(text);
        }
        r.setPrefSize(40, 40);
        r.setStyle("-fx-border-color: #89C68D; -fx-border-width: 2px");
        return r;
    }
}
