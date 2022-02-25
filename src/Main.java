import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    private BorderPane mainPane = new BorderPane();
    private List<Integer> array = new LinkedList<>();

    @Override
    public void start(Stage stage) {
        Button sort = new Button("sort");
        Button shuffle = new Button("shuffle");
        Button reverse = new Button("reverse");

        sort.setOnAction(event -> {
            Object[] temp = this.array.toArray();
            Arrays.sort(temp);

            this.array.clear();
            for (Object o : temp) {
                this.array.add((int) o);
            }
            mainPane.setCenter(new TextField(Arrays.toString(new List[]{this.array})));
        });

        shuffle.setOnAction(event -> {
            Object[] temp = this.array.toArray();
            for (int i = 0; i < this.array.size(); i++) {
                if(i < this.array.size() - 2) {
                    Object tempnum = temp[i + 2];
                    temp[i + 2] = temp[i];
                    temp[i] = tempnum;
                }
            }

            this.array.clear();
            for (Object o : temp) {
                this.array.add((int) o);
            }
            mainPane.setCenter(new TextField(Arrays.toString(new List[]{this.array})));
        });

        reverse.setOnAction(event -> {
            Object[] temp = this.array.toArray();
            Arrays.sort(temp, Collections.reverseOrder());

            this.array.clear();
            for (Object o : temp) {
                this.array.add((int) o);
            }
            mainPane.setCenter(new TextField(Arrays.toString(new List[]{this.array})));
        });

        HBox hBox = new HBox(sort, shuffle, reverse);
        mainPane.setBottom(hBox);

        TextArea input = new TextArea();
        Label text = new Label("Enter a number:");
        Button send = new Button("send");
        HBox hbox1 = new HBox(text, input, send);
        mainPane.setTop(hbox1);
        send.setOnAction(event -> {
            String data = input.getText();
            for (char element : data.toCharArray()) {
                try {
                    if (element != ' ' && element != ',') {
                        this.array.add(Integer.parseInt(element + ""));
                    }
                }catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Only numbers allowed");
                    e.printStackTrace();
                }
            }
            mainPane.setCenter(new TextField(Arrays.toString(new List[]{this.array})));
        });

        mainPane.setCenter(new TextField());
        stage.setScene(new Scene(mainPane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}