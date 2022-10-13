package com.example.t2fx;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Painter extends Application {

    public void start(Stage primaryStage) {
        root.getChildren().add(canvBox);
        root.getChildren().add(strings);

        canvBox.setPadding(new Insets(270, 0, 270, 0));
        canvBox.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();
        context.transform(1, 0, 0, -1, 0, canvas.getHeight());


        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(drawBox);
        strings.getChildren().add(translationBox);
        strings.getChildren().add(scaleBox);
        strings.getChildren().add(compressionBox);
        strings.getChildren().add(spinBox);
        strings.getChildren().add(cleanBox);

        drawBox.setSpacing(10);
        drawBox.getChildren().add(buttonDraw);
        drawBox.getChildren().add(new Text("x: "));
        drawBox.getChildren().add(x0);
        drawBox.getChildren().add(new Text("y: "));
        drawBox.getChildren().add(y0);
        drawBox.getChildren().add(new Text("width: "));
        drawBox.getChildren().add(width);
        drawBox.getChildren().add(new Text("height: "));
        drawBox.getChildren().add(height);

        translationBox.setSpacing(10);
        translationBox.getChildren().add(buttonTranslation);
        translationBox.getChildren().add(new Text("x: "));
        translationBox.getChildren().add(xTran);
        translationBox.getChildren().add(new Text("y: "));
        translationBox.getChildren().add(yTran);

        scaleBox.setSpacing(10);
        scaleBox.getChildren().add(buttonScale);
        scaleBox.getChildren().add(new Text("Scale: "));
        scaleBox.getChildren().add(scale);

        compressionBox.setSpacing(10);
        compressionBox.getChildren().add(buttonCompression);
        compressionBox.getChildren().add(new Text("Amount of compression: "));
        compressionBox.getChildren().add(amount);

        spinBox.setSpacing(10);
        spinBox.getChildren().add(buttonSpin);
        spinBox.getChildren().add(new Text("Angle of spin: "));
        spinBox.getChildren().add(angle);

        cleanBox.setSpacing(10);
        cleanBox.getChildren().add(buttonClean);

        buttonDraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                double[][] arr = Create.create(Double.parseDouble(x0.getText()) + CANVASWIDTH / 2,
                        Double.parseDouble(y0.getText()) + CANVASHEIGHT / 2,
                        Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));

                draw(arr, context);

            }
        });
        buttonTranslation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                context.setStroke(Color.CORAL);

                double[][] arr = Create.create(Double.parseDouble(x0.getText()) + CANVASWIDTH / 2,
                        Double.parseDouble(y0.getText()) + CANVASHEIGHT / 2,
                        Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));


                double x = Double.parseDouble(xTran.getText());
                double y = Double.parseDouble(yTran.getText());

                arr = Translation.translation(arr, x, y);


                draw(arr, context);

            }
        });
        buttonScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                context.setStroke(Color.BLUEVIOLET);

                double[][] arr = Create.create(Double.parseDouble(x0.getText()) + CANVASWIDTH / 2,
                        Double.parseDouble(y0.getText()) + CANVASHEIGHT / 2,
                        Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));

                double s = Double.parseDouble(scale.getText());

                Point2D p = new Point2D(arr[0][0], arr[0][1]);
                arr = Scale.scale(arr, p, s);
                draw(arr, context);

            }
        });
        buttonCompression.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                context.setStroke(Color.MAGENTA);

                double[][] arr = Create.create(Double.parseDouble(x0.getText()) + CANVASWIDTH / 2,
                        Double.parseDouble(y0.getText()) + CANVASHEIGHT / 2,
                        Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));

                double f = Double.parseDouble(amount.getText());
                Point2D p = new Point2D(arr[0][0], arr[0][1]);
                arr = Compression.compression(arr, p, f);
                draw(arr, context);
            }
        });
        buttonSpin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                context.setStroke(Color.PLUM);
                double[][] arr = Create.create(Double.parseDouble(x0.getText()) + CANVASWIDTH / 2,
                        Double.parseDouble(y0.getText()) + CANVASHEIGHT / 2,
                        Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));

                double angle1 = Double.parseDouble(angle.getText());
                Point2D p = new Point2D(arr[0][0], arr[0][1]);
                arr = Spin.spin(arr, p, angle1);
                draw(arr, context);

            }
        });

        buttonClean.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                context.setStroke(Color.BLACK);
                double[][] arr = Create.create(Double.parseDouble(x0.getText()) + CANVASWIDTH / 2,
                        Double.parseDouble(y0.getText()) + CANVASHEIGHT / 2,
                        Double.parseDouble(width.getText()), Double.parseDouble(height.getText()));

                context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                draw(arr, context);

                context.strokeLine(CANVASWIDTH / 2, 0, CANVASWIDTH / 2, CANVASHEIGHT);
                context.strokeLine(0, CANVASHEIGHT / 2, CANVASWIDTH, CANVASHEIGHT / 2);
            }
        });

        Line line4 = new Line(0, 270, 1000, 270);
        root.getChildren().add(line4);

        context.strokeLine(CANVASWIDTH / 2, 0, CANVASWIDTH / 2, CANVASHEIGHT);
        context.strokeLine(0, CANVASHEIGHT / 2, CANVASWIDTH, CANVASHEIGHT / 2);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void draw(double[][] arr, GraphicsContext context) {
        Point2D p0 = new Point2D(arr[0][0], arr[0][1]);
        Point2D p1 = new Point2D(arr[1][0], arr[1][1]);
        Point2D p2 = new Point2D(arr[2][0], arr[2][1]);
        Point2D p3 = new Point2D(arr[3][0], arr[3][1]);

        context.strokeLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
        context.strokeLine(p0.getX(), p0.getY(), p2.getX(), p2.getY());
        context.strokeLine(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        context.strokeLine(p3.getX(), p3.getY(), p1.getX(), p1.getY());
    }

    public static void main(String[] args) {
        launch(args);
    }

    Group root = new Group();

    VBox strings = new VBox();
    VBox canvBox = new VBox();

    Canvas canvas = new Canvas(1000, 530);


    Button buttonDraw = new Button("Нарисовать фигуру");
    Button buttonTranslation = new Button("1. Перенос");
    Button buttonScale = new Button("2. Масштабирование");
    Button buttonCompression = new Button("3. Сжатие к прямой");
    Button buttonSpin = new Button("4. Поврот - вращение");
    Button buttonClean = new Button("Очистить экран от преобразований");

    HBox drawBox = new HBox();
    HBox translationBox = new HBox();
    HBox scaleBox = new HBox();
    HBox compressionBox = new HBox();
    HBox spinBox = new HBox();
    HBox cleanBox = new HBox();

    TextField x0 = new TextField();
    TextField y0 = new TextField();
    TextField width = new TextField();
    TextField height = new TextField();
    TextField xTran = new TextField();
    TextField yTran = new TextField();
    TextField scale = new TextField();
    TextField amount = new TextField();
    TextField angle = new TextField();

    final private int WIDTH = 1000;
    final private int HEIGHT = 800;

    final private int BUTTONBLOCKWIDTH = 270;

    final private int CANVASWIDTH = 1000;
    final private int CANVASHEIGHT = 530;

    private ArrayList<String> afs = new ArrayList<>();

}
