package com.example.project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;

public class mainController implements Initializable {

    // массив мест на парковке
    String[][] places = new String[30][5];

    // массив тарифов
    String[][] rates = new String[5][3];

    // кнопки для управления местом
    @FXML
    Button btnPlaceOne;
    @FXML
    Button btnPlaceTwo;
    @FXML
    Button btnPlaceThree;
    @FXML
    Button btnPlaceFour;
    @FXML
    Button btnPlaceFive;
    @FXML
    Button btnPlaceSix;
    @FXML
    Button btnPlaceSeven;
    @FXML
    Button btnPlaceEight;
    @FXML
    Button btnPlaceNine;
    @FXML
    Button btnPlaceTen;
    ArrayList<Button> buttons = new ArrayList<>();

    // кнопка занятия места
    @FXML
    Button btnTake;

    // выпадающий список выбора часов стоянки
    @FXML
    ComboBox cmbTime;

    // radiobutton выбора этажа
    @FXML
    RadioButton radFloorOne;
    @FXML
    RadioButton radFloorTwo;
    @FXML
    RadioButton radFloorThree;

    // изменяемые метки
    @FXML
    Label lblTime;
    @FXML
    Label lblRate;
    @FXML
    Label lblPrice;
    @FXML
    Label lblPlace;
    @FXML
    Label lblPriceWRate;
    @FXML
    Label lblTaken;

    // изображение парковки
    @FXML
    ImageView imgBox;

    // переменные времени
    LocalTime midnight = LocalTime.MIDNIGHT;
    LocalTime morningStart = LocalTime.of(4, 0);
    LocalTime morningEnd = LocalTime.of(9, 0);
    LocalTime peakStart = LocalTime.of(13,0);
    LocalTime peakEnd = LocalTime.of(15,0);
    LocalTime eveningStart = LocalTime.of(18, 0);
    LocalTime eveningEnd = LocalTime.of(21, 0);

    @FXML
    public void initialize(URL location, ResourceBundle resources){
        buttons.add(btnPlaceOne); // добавление всех кнопок мест в коллекцию
        buttons.add(btnPlaceTwo);
        buttons.add(btnPlaceThree);
        buttons.add(btnPlaceFour);
        buttons.add(btnPlaceFive);
        buttons.add(btnPlaceSix);
        buttons.add(btnPlaceSeven);
        buttons.add(btnPlaceEight);
        buttons.add(btnPlaceNine);
        buttons.add(btnPlaceTen);
        getConnection(); // установка соединения с базой данных и заполнение матриц
        cmbTime.getItems().removeAll(cmbTime.getItems()); // инициализация элементов comboBox
        cmbTime.getItems().addAll("1", "2", "3", "6", "9");
        cmbTime.getSelectionModel().select("1");
        setButtonsFloorOne(); // установка кнопок в позицию первого этажа
        ToggleGroup radioGroup = new ToggleGroup(); // создание группы переключателей
        radFloorOne.setToggleGroup(radioGroup);
        radFloorTwo.setToggleGroup(radioGroup);
        radFloorThree.setToggleGroup(radioGroup);
        String imageString = mainApplication.class.getResource("images/etaj1.png").toExternalForm();
        imgBox.setImage(new Image(imageString));
        lblTaken.setTextAlignment(TextAlignment.CENTER);
        lblTaken.setTextFill(Paint.valueOf("Red"));
        lblPlace.setText("Место: 1"); // изначально выбранное место
        radFloorOne.setSelected(true); // изначально выбранный этаж
        getTime(); // установка времени и тарифа
        getMoney(); // установка стоимости
        checkBusy(); // проверка занятых мест
        EventHandler<ActionEvent> handler = event -> { // создаем обработчик событий нажатия на кнопку
            lblTaken.setText("");
            btnTake.setDisable(false);
            if (event.getSource() == btnPlaceOne) {
                lblPlace.setText("Место: 1");
                getMoney();
                if(!btnPlaceOne.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceTwo) {
                lblPlace.setText("Место: 2");
                getMoney();
                if(!btnPlaceTwo.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceThree) {
                lblPlace.setText("Место: 3");
                getMoney();
                if(!btnPlaceThree.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceFour) {
                lblPlace.setText("Место: 4");
                getMoney();
                if(!btnPlaceFour.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceFive) {
                lblPlace.setText("Место: 5");
                getMoney();
                if(!btnPlaceFive.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceSix) {
                lblPlace.setText("Место: 6");
                getMoney();
                if(!btnPlaceSix.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceSeven) {
                lblPlace.setText("Место: 7");
                getMoney();
                if(!btnPlaceSeven.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceEight) {
                lblPlace.setText("Место: 8");
                getMoney();
                if(!btnPlaceEight.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else if (event.getSource() == btnPlaceNine) {
                lblPlace.setText("Место: 9");
                getMoney();
                if(!btnPlaceNine.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
            else {
                lblPlace.setText("Место: 10");
                getMoney();
                if(!btnPlaceTen.getBackground().getImages().isEmpty()) {
                    btnTake.setDisable(true);
                    lblTaken.setText("Занято!");
                }
            }
        };
        for (var btn: buttons) {
            btn.setOnAction(handler);
        }
    }

    // проверка выбранного этажа на занятое место
    private void checkBusy() {
        if (radFloorOne.isSelected()) { // если выбран первый этаж
            for (int i = 0; i < 10; i++) {
                if (places[i][4].equals("t")) // если место занято
                    setPictureBtn(i); // то устанавливаем картинку занятости
                else
                    buttons.get(i).setStyle("-fx-background-color: transparent"); // иначе делаем кнопку прозрачной
            }
        } else if (radFloorTwo.isSelected()) { // если выбран второй этаж
            for (int i = 10; i < 20; i++) {
                if (places[i][4].equals("t"))
                    setPictureBtn(i-10); // на каждом этаже 10 мест, а во втором этаже счет начинается с 20, поэтому i-10
                else
                    buttons.get(i-10).setStyle("-fx-background-color: transparent");
            }
        } else {
            for (int i = 20; i < 30; i++) { // если выбран третий этаж
                if (places[i][4].equals("t"))
                    setPictureBtn(i-20); // на каждом этаже 10 мест, а на третьем этаже счет начинается с 30, поэтому i-10
                else
                    buttons.get(i-20).setStyle("-fx-background-color: transparent");
            }
        }
    }

    // установка случайной картинки в занятом месте
    private void setPictureBtn(int i) {
        Random rand = new Random();
        switch (rand.nextInt(6)) { // выбор псевдослучайного числа
            case 0: // установка картинки и её свойств
                buttons.get(i).setStyle("-fx-background-image: url('file:/H:/un1que/images/car1.png'); -fx-background-color: transparent; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
                break;
            case 1: // установка картинки и её свойств
                buttons.get(i).setStyle("-fx-background-image: url('file:/H:/un1que/images/car2.png'); -fx-background-color: transparent; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
                break;
            case 2: // установка картинки и её свойств
                buttons.get(i).setStyle("-fx-background-image: url('file:/H:/un1que/images/car3.png'); -fx-background-color: transparent; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
                break;
            case 3: // установка картинки и её свойств
                buttons.get(i).setStyle("-fx-background-image: url('file:/H:/un1que/images/car4.png'); -fx-background-color: transparent; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
                break;
            case 4: // установка картинки и её свойств
                buttons.get(i).setStyle("-fx-background-image: url('file:/H:/un1que/images/car5.png'); -fx-background-color: transparent; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
                break;
            case 5: // установка картинки и её свойств
                buttons.get(i).setStyle("-fx-background-image: url('file:/H:/un1que/images/car6.png'); -fx-background-color: transparent; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
                break;
        }
    }

    // изменение картинки этажа при выборе другого этажа в radioButton
    @FXML
    public void checkChanged() {
        if (radFloorOne.isSelected()) { // если выбран первый этаж
            setButtonsFloorOne(); // устанавливаем положение кнопок для первого этажа и добавляем картинку этажа
            String imageString = mainApplication.class.getResource("images/etaj1.png").toExternalForm();
            imgBox.setImage(new Image(imageString));
        }
        else if (radFloorTwo.isSelected()) { // если выбран второй этаж
            setButtonsFloorTwo(); // устанавливаем положение кнопок для второго этажа и добавляем картинку этажа
            String imageString = mainApplication.class.getResource("images/etaj2.png").toExternalForm();
            imgBox.setImage(new Image(imageString));
            }
            else if (radFloorThree.isSelected()) { // если выбран третий этаж
                setButtonsFloorThree(); // устанавливаем положение кнопок для третьего этажа и добавляем картинку этажа
                String imageString = mainApplication.class.getResource("images/etaj3.png").toExternalForm();
                imgBox.setImage(new Image(imageString));
                }
        checkBusy(); // проверка на занятые места
    }

    // отдельный поток получения времени и определения тарифа
    protected void getTime() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) { // бесконечный цикл
                    try {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                LocalTime time = LocalTime.now(); // получаем текущее время
                                lblTime.setText(time.toString().substring(0,8)); // устанавливаем время на форме
                                if (time.compareTo(midnight) > 0 && time.compareTo(morningStart) < 0)
                                    lblRate.setText(rates[0][1]); // ночной тариф
                                else if (time.compareTo(morningStart) > 0 && time.compareTo(morningEnd) < 0)
                                    lblRate.setText(rates[2][1]); // утренний тариф
                                else if (time.compareTo(morningEnd) > 0 && time.compareTo(peakStart) < 0)
                                    lblRate.setText(rates[1][1]); // дневной тариф
                                else if (time.compareTo(peakStart) > 0 && time.compareTo(peakEnd) < 0)
                                    lblRate.setText(rates[4][1]); // пиковый тариф
                                else  if (time.compareTo(peakEnd) > 0 && time.compareTo(eveningStart) < 0)
                                    lblRate.setText(rates[1][1]); // дневной тариф
                                else if (time.compareTo(eveningStart) > 0 && time.compareTo(eveningEnd) < 0)
                                    lblRate.setText(rates[3][1]); // вечерний тариф
                                else lblRate.setText(rates[0][1]); // ночной тариф
                            }
                        });
                        Thread.sleep(1000); // поток ждет секунду
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true); // daemon thread - фоновый поток, обслуживающий основной
        thread.start(); // запуск потока
    }

    // обработчик события изменения часов оплачиваемой стоянки в comboBox
    @FXML
    protected void onTimeChanged() {
        getMoney(); // получить стоимость места с учетом текущего времени и необходимого времени стоянки
    }

    // заполнение метки стоимости и стоимости с тарифом
    protected void getMoney() {
        String[] thisPlace = lblPlace.getText().split("\\s+"); // получаем выбранное место
        double money = Double.parseDouble(places[Integer.parseInt(thisPlace[1])][3]); // получаем стоимость выбранного места
        double rateBonus = 0;
        switch(lblRate.getText()) { // выбираем доплату за тариф
            case "Ночной":
                rateBonus = Double.parseDouble(rates[0][2]);
                break;
            case "Дневной":
                rateBonus = Double.parseDouble(rates[1][2]);
                break;
            case "Утренний":
                rateBonus = Double.parseDouble(rates[2][2]);
                break;
            case "Вечерний":
                rateBonus = Double.parseDouble(rates[3][2]);
                break;
            case "Час пик":
                rateBonus = Double.parseDouble(rates[4][2]);
                break;
        }
        lblPrice.setText(String.valueOf(money * Double.parseDouble(cmbTime.getValue().toString()))); // выводим стоимость
        money = (money + rateBonus) * Double.parseDouble(cmbTime.getValue().toString());
        lblPriceWRate.setText("Стоимость с тарифом: " + money); // выводим стоимость с тарифом
    }

    private void getConnection() {
        try{
            Class.forName("org.postgresql.Driver"); // используемый класс
            String url = "jdbc:postgresql://localhost/autopark"; // строка подключения

            Properties authorization = new Properties(); // свойства подключения к бд
            authorization.setProperty("user", "user"); // логин
            authorization.setProperty("password", "12345"); // пароль
            Connection connection = DriverManager.getConnection(url, authorization); // поле подключения
            // создаем выражение, которое отражает реальное представление данных в базе данных
            // и позволяет не только обновлять выбранные записи, но и создавать новые
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // указываем запрос для выполнения и результирующий рабор данных
            ResultSet table = statement.executeQuery("SELECT * FROM public.places ORDER BY id_place ASC;");

            table.first(); // устанавливает курсор на первое место
            int j = 0, k = 0;
            do {
                for(int i = 1; i <= table.getMetaData().getColumnCount(); i++) { // получаем запись
                    places[j][k] = table.getString(i); k++; // заполняем матрицу мест значениями из бд
                    if(i % 5 == 0) { // переход на второе измерение для новой записи
                        j++;
                        k = 0;
                    }
                }
            }
            while(table.next()); // устанавливает курсор на следующую запись, пока они есть

            // указываем запрос для выполнения и результирующий рабор данных
            ResultSet table2 = statement.executeQuery("SELECT * FROM public.rates ORDER BY id_rate ASC;");

            table2.first(); // устанавливает курсор на первое место
            j = 0; k = 0;
            do {
                for(int i = 1; i <= table2.getMetaData().getColumnCount(); i++) {
                    rates[j][k] = table2.getString(i); k++; // заполняем тарифов значениями из бд
                    if(i % 3 == 0) { // переход на второе измерение для новой записи
                        j++;
                        k = 0;
                    }
                }
            }
            while(table2.next()); // устанавливает курсор на следующую запись, пока они есть

            if(table != null) table.close(); // закрываем результирующий набор 1
            if(table2 != null) table2.close(); // закрываем результирующий набор 2
            if(statement != null) statement.close(); // закрываем выражение
            if(connection != null) connection.close(); // закрываем подключение
        }
        catch (Exception e){
            System.err.println("Ошибка доступа к БД!");
            e.printStackTrace();
        }
    }

    protected void setButtonsFloorOne() {
        for (Button btn: buttons) {
            btn.setText("");
            btn.setPrefSize(55,90); // вертикальное расположение всех кнопок
        }
        // 1 ряд
        btnPlaceOne.setLayoutX(191);
        btnPlaceOne.setLayoutY(14);
        btnPlaceTwo.setLayoutX(257);
        btnPlaceTwo.setLayoutY(14);
        btnPlaceThree.setLayoutX(323);
        btnPlaceThree.setLayoutY(14);
        btnPlaceFour.setLayoutX(389);
        btnPlaceFour.setLayoutY(14);
        btnPlaceFive.setLayoutX(455);
        btnPlaceFive.setLayoutY(14);
        // 2 ряд
        btnPlaceSix.setLayoutX(170);
        btnPlaceSix.setLayoutY(165);
        btnPlaceSeven.setLayoutX(236);
        btnPlaceSeven.setLayoutY(165);
        btnPlaceEight.setLayoutX(302);
        btnPlaceEight.setLayoutY(165);
        btnPlaceNine.setLayoutX(368);
        btnPlaceNine.setLayoutY(165);
        btnPlaceTen.setLayoutX(434);
        btnPlaceTen.setLayoutY(165);
    }

    protected void setButtonsFloorTwo() {
        // 1 ряд
        btnPlaceOne.setLayoutX(215);
        btnPlaceOne.setLayoutY(14);
        btnPlaceTwo.setLayoutX(281);
        btnPlaceTwo.setLayoutY(14);
        btnPlaceThree.setLayoutX(347);
        btnPlaceThree.setLayoutY(14);
        btnPlaceFour.setLayoutX(413);
        btnPlaceFour.setLayoutY(14);
        btnPlaceFive.setLayoutX(479);
        btnPlaceFive.setLayoutY(14);
        // 2 ряд
        btnPlaceSix.setPrefSize(90,55); // горизонтальное расположение кнопки
        btnPlaceSeven.setPrefSize(90,55); // горизонтальное расположение кнопки
        btnPlaceSix.setLayoutX(125);
        btnPlaceSix.setLayoutY(115);
        btnPlaceSeven.setLayoutX(125);
        btnPlaceSeven.setLayoutY(181);
        btnPlaceEight.setLayoutX(332);
        btnPlaceEight.setLayoutY(165);
        btnPlaceNine.setLayoutX(398);
        btnPlaceNine.setLayoutY(165);
        btnPlaceTen.setLayoutX(464);
        btnPlaceTen.setLayoutY(165);
    }

    protected void setButtonsFloorThree() {
        btnPlaceSix.setPrefSize(55,90); // вертикальное расположение кнопки
        btnPlaceSeven.setPrefSize(55,90); // вертикальное расположение кнопки
        // 1 ряд
        btnPlaceOne.setLayoutX(170);
        btnPlaceOne.setLayoutY(14);
        btnPlaceTwo.setLayoutX(236);
        btnPlaceTwo.setLayoutY(14);
        btnPlaceThree.setLayoutX(302);
        btnPlaceThree.setLayoutY(14);
        btnPlaceFour.setLayoutX(368);
        btnPlaceFour.setLayoutY(14);
        btnPlaceFive.setLayoutX(434);
        btnPlaceFive.setLayoutY(14);
        // 2 ряд
        btnPlaceSix.setLayoutX(188);
        btnPlaceSix.setLayoutY(165);
        btnPlaceSeven.setLayoutX(254);
        btnPlaceSeven.setLayoutY(165);
        btnPlaceEight.setLayoutX(320);
        btnPlaceEight.setLayoutY(165);
        btnPlaceNine.setLayoutX(386);
        btnPlaceNine.setLayoutY(165);
        btnPlaceTen.setLayoutX(452);
        btnPlaceTen.setLayoutY(165);
    }

    @FXML
    protected void onTakeButtonClick() {
        try {
            Class.forName("org.postgresql.Driver"); // используемый класс
            String url = "jdbc:postgresql://localhost/autopark"; // строка подключения

            Properties authorization = new Properties(); // свойства подключения к бд
            authorization.setProperty("user", "user"); // логин
            authorization.setProperty("password", "12345"); // пароль
            Connection connection = DriverManager.getConnection(url, authorization); // поле подключения

            // подготавливаем запрос на обновление данных
            PreparedStatement stmt = connection.prepareStatement("UPDATE public.places SET taken = true WHERE id_place = ?;");

            String[] thisPlace = lblPlace.getText().split("\\s+"); // получаем id места
            int place = Integer.parseInt(thisPlace[1]);
            if (radFloorTwo.isSelected()) { // если выбран первый этаж
                place += 10; // увеличиваем id на 10, поскольку второй этаж начинается с id 10
            }
            else if (radFloorThree.isSelected()) { // если выбран второй этаж
                place += 20; // увеличиваем id на 20, поскольку третий этаж начинается с id 20
            }

            stmt.setInt(1, place); // устанавливаем параметр для запроса
            stmt.executeUpdate(); // выполняем запрос на обновление

            if(stmt != null) stmt.close(); // закрываем выражение
            if(connection != null) connection.close(); // закрываем подключение

            places[place-1][4] = "t"; // устанавливаем занятость места
            checkBusy(); // проверяем занятые места для обновления формы
        }
        catch (Exception e){
            System.err.println("Ошибка доступа к БД!");
            e.printStackTrace();
        }
    }
}