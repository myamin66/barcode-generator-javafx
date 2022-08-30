package com.i2p.barcodeusingjavafx;

import com.google.zxing.WriterException;
import com.i2p.barcodeusingjavafx.services.CategoriesService;
import com.i2p.barcodeusingjavafx.services.ProductsService;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javax.inject.Inject;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class  BarcodeController implements Initializable {
    @FXML private TextField reference;
    @FXML private ComboBox productcombo;
    @FXML private ComboBox categorycombo;

    @FXML private ImageView imgplace;
    @FXML private Spinner<Integer> rowspinner;
    @FXML private Spinner<Integer> colsspinner;
//    @FXML private Button printbutton;


    @Inject
    ProductsService pservice;

    @Inject
    CategoriesService cservice;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String>  categorylist= cservice.findNameOfCategory();
        ObservableList<String> list2 = FXCollections.observableArrayList(categorylist);
        categorycombo.setItems(list2);

    }



    public void showReference(ActionEvent actionEvent) throws IOException {
        String pName = productcombo.getSelectionModel().getSelectedItem().toString();
        String referenceProduct = pservice.findProductsReferenceByName(pName);
        reference.setText(referenceProduct);
        javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(getBarCodeArray().toByteArray()));
        imgplace.setImage(img);

//        File file2 = new File(productcombo.getSelectionModel().getSelectedItem().toString()+".pdf");
//        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file2.getAbsolutePath());
    }

    public void setProducts(ActionEvent actionEvent) {
     try{
         String name = categorycombo.getSelectionModel().getSelectedItem().toString();
        String productCatId = cservice.findCategoryIdByName(name);
        List<String> productList = pservice.findProductNamesByCatId(productCatId);
        ObservableList<String> list = FXCollections.observableArrayList(productList);
        productcombo.setItems(list);
//        if(rowspinner.getValue()>0 && colsspinner.getValue()>0) {
////            barcodebtn.setDisable(false);
//        }
     } catch (Exception e){
         e.printStackTrace();
         System.out.println(e.getMessage());
     }
    }

//    public void printBarcode(ActionEvent actionEvent) throws IOException, DocumentException {
//        preparPDF();
//        File file2 = new File(productcombo.getSelectionModel().getSelectedItem().toString()+".pdf");
//        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file2.getAbsolutePath());
//    }

    private void preparPDF() throws FileNotFoundException, IOException, BadElementException, DocumentException {
        ByteArrayOutputStream baos = getBarCodeArray();
        Image png = Image.getInstance(baos.toByteArray());

        png.setAbsolutePosition(0, 705);
        png.scalePercent(25);

        Document document;
        document = new Document();
        PdfPTable table = new PdfPTable(colsspinner.getValue());
        int rows = rowspinner.getValue();

        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        for (int aw = 0; aw < (rows*(colsspinner.getValue())); aw++) {
            Paragraph p = new Paragraph("       ");
            p.add("\n        ");
            PdfPTable intable = new PdfPTable(1);
            intable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            intable.addCell(p);
            intable.addCell(png);
            intable.getDefaultCell().setBorder(0);

            table.addCell(intable);
        }
        Paragraph p = new Paragraph("");
        p.add("\n");
        p.add(png);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(productcombo.getSelectionModel().getSelectedItem().toString()+".pdf"));
        document.open();
        document.add(table);
        document.close();
        writer.close();
    }
    public void checkrowspinner(MouseEvent mouseEvent) {
//        if(rowspinner.getValue()!=0 && colsspinner.getValue()!=0){
//            barcodebtn.setDisable(false);
//        }else{
//            barcodebtn.setDisable(true);
//        }
    }

    public void help(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help Center");
        alert.setHeaderText("Please do visit if you need help");
        alert.setContentText("We have proper documentation for project\nDo Visit on i2p.com.pk/barcod-eproject");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    public ByteArrayOutputStream getBarCodeArray() throws IOException{
        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15f);
        code128.setModuleWidth(0.3);
        code128.setQuietZone(10);
        code128.doQuietZone(true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 400, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        code128.generateBarcode(canvas, reference.getText());
        canvas.finish();

        return baos;
    }


    public void printbyimage(MouseEvent event) throws IOException, DocumentException {
        preparPDF();
        File file2 = new File(productcombo.getSelectionModel().getSelectedItem().toString()+".pdf");
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file2.getAbsolutePath());
    }
}