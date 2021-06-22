package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * -- Created by Guilherme Humberto Dias --
 * -- Instituto de Previdência do Estado de Santa Catarina | 2017 --
 * -- Sistema SAP --
 */
public class FonteRecursoController {

    @FXML public Button fecharButton, cadastrarFonteRecursoButton;

    public PrincipalController principalController;

    public void setPrincipalController(PrincipalController principal) {
        principalController = principal;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void fecharJanela() {
        fecharButton.getScene().getWindow().hide();
    }

    @FXML
    public void cadastrarFonteRecurso() {

    }





}
