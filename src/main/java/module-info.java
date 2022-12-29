module com.projekthibernate.projekthibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires org.hibernate.orm.core;
    requires java.persistence;

    //opens com.projekthibernate.projekthibernate.POJO to org.hibernate.orm.core;
    opens com.projekthibernate to javafx.fxml;
    exports com.projekthibernate;
    opens com.projekthibernate.POJO to javafx.fxml,org.hibernate.orm.core;
    exports com.projekthibernate.POJO;
    exports com.projekthibernate.Controllers;
    opens com.projekthibernate.Controllers to javafx.fxml;
}