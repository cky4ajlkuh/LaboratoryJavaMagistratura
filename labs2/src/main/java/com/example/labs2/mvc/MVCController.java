package com.example.labs2.mvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class MVCController implements Initializable {

    @FXML
    public LineChart<Double, Double> chartOfFunction;
    @FXML
    public TableColumn<Point, String> columnCoordinateX;
    @FXML
    public TableColumn<Point, String> columnCoordinateY;
    @FXML
    public TableView<Point> tableOfFunction;
    @FXML
    public Label typeOfFunction;
    @FXML
    public Button addButton;
    @FXML
    public Button removeButton;
    @FXML
    public TextField valueX;
    private Model model;
    private ObservableList<Point> data;
    private Graphics chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
        data = FXCollections.observableArrayList();
        columnCoordinateX.setEditable(true);
        columnCoordinateX.setCellValueFactory(new PropertyValueFactory<>("X"));
        columnCoordinateY.setCellValueFactory(new PropertyValueFactory<>("Y"));
        tableOfFunction.setItems(data);
        changeElements();
        chart = new Graphics(chartOfFunction);
        chartOfFunction.setVisible(true);
    }

    public void addData() {
        if (addButton.isPressed()) {
            getX();
        }
    }

    public void removeData() {
        TableView.TableViewSelectionModel<Point> selectionModel = tableOfFunction.getSelectionModel();
        ObservableList<Integer> indices = selectionModel.getSelectedIndices();
        Integer[] selectionIndices = new Integer[indices.size()];
        selectionIndices = indices.toArray(selectionIndices);
        Arrays.sort(selectionIndices);
        for (Integer selectionIndex : selectionIndices) {
            selectionModel.clearSelection(selectionIndex);
            tableOfFunction.getItems().remove(selectionIndex.intValue());
            model.remove(selectionIndex);
        }
        draw();
    }

    private void changeElements() {
        columnCoordinateX.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCoordinateX.setOnEditCommit(event -> {
            Point point = event.getTableView().getItems().get(event.getTablePosition().getRow());
            point.setX(event.getNewValue());
            draw();
        });
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Not value of Double insert!");
            return false;
        }
    }

    public void draw() {
        XYChart.Series<Double, Double> points = new XYChart.Series();
        for (int i = 0; i < model.getSize(); ++i) {
            Double x = Double.valueOf(model.getPoint(i).getX());
            Double y = Double.valueOf(model.getPoint(i).getY());
            points.getData().add(new XYChart.Data(x, y));
        }
        chart.clear();
        chart.drawLines(points);
    }

    public void enterData(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            getX();
        }
    }

    private void getX() {
        if (isDouble(valueX.getText()) && !model.contains(Double.parseDouble(valueX.getText()))) {
            model.add(Double.parseDouble(valueX.getText()));
            valueX.clear();
            data.add(model.getPoint(model.getSize() - 1));
            tableOfFunction.setItems(data);
            draw();
        }
    }
}
