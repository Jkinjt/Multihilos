module com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;

    opens com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez to javafx.fxml;
    exports com.joaquinjimenez.ProyectoMultihilo_JoaquinJimenez;
}
