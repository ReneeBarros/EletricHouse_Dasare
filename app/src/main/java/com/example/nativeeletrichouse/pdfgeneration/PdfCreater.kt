package com.example.nativeeletrichouse.pdfgeneration

import android.annotation.SuppressLint
import com.example.nativeeletrichouse.data.reponse.ResponseCaculateAmbiente
import com.example.nativeeletrichouse.data.reponse.ResponseCalulatePdf
import com.example.nativeeletrichouse.maper.MapperResponseApiToResponseUi
import com.itextpdf.kernel.colors.Color
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.Style
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.Leading
import com.itextpdf.layout.properties.Property
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import java.io.OutputStream

class PdfCreater(

) {

    @SuppressLint("SuspiciousIndentation")
    fun createPdf(
        outputStream: OutputStream,
        response:List<MapperResponseApiToResponseUi>
    ){
        // Criar o escritor de PDF
        val pdfWriter = PdfWriter(outputStream)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument, PageSize(842f, 595f)).apply {
            setMargins(50f,10f,20f,10f)
            setProperty(
                Property.LEADING,
                Leading(Leading.MULTIPLIED, 1f)
            )
        }.setWordSpacing(0f)

        val page = pdfDocument.addNewPage()

        //Criando Tabela

        val tabelaPrimaria = Table(26,true)
            .setMarginLeft(6f)
            .setMarginRight(6f)
            //.setWidth(page.pageSize.width - 20f - 30f)

        // Criando Tema Princial
        val nomeEmpresa = Paragraph("Eletric House")
            .setBold()
            .setFontSize(32f)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
            .setHorizontalAlignment(HorizontalAlignment.CENTER)

        tabelaPrimaria.addCell(
            Cell(2,26).add(nomeEmpresa)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBackgroundColor(DeviceRgb(211,211,211))
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
        )
/*        //Linah separador de tabela
        val line = LineSeparator(
            SolidLine().apply {
                color = DeviceRgb(204, 204, 204)
            }
        ).setMarginTop(20f)*/

        val listaTitulosTabelaPrimaria =  listOf(

            "Ambiente",
            "Nome Ambiente",
            "Largura",
            "Comprimento",
            "Area",
            "Tensao",
            "Lum.Amb",
            "Lum.Lamp",
            "(W)Lamp",
            "Total-Lamp",
            "Lumens Total",
            "(W)Total Ilum",
            "(A)Circ. Ilum",
            "Qtde Tomada",
            "(W)Total Tomada",
            "(A)Tomada",
            "Qtde Pessoas Amb.",
            "Qtde Elet.",
            "Btus por M2",
            "Btus Adic.Pes",
            "Btus Adic.Ele",
            "Btus Adic.Inc.Solar",
            "Btus Total",
            "IDRS",
            "(W) Eletria Ac",
            "(A) Circuito AC"
        )

        val listaParagraphTitulos: MutableList<Paragraph> = mutableListOf()

        listaTitulosTabelaPrimaria.forEach {
            listaParagraphTitulos.add(createBoldTextParagraph(it)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setFontSize(10f)
                .setBorder(null)
                .setBold()
                //.setBorderBottom(SolidBorder(DeviceRgb(204, 204, 204), 1f))
            )
        }

        val listaParagraphConteudos: MutableList<Paragraph> = mutableListOf()

        listaParagraphTitulos.forEach {
            tabelaPrimaria.addCell(it)
        }

        response.forEach { it ->
            it.listaAmbienteResponse.forEach {
                listaParagraphConteudos.add(createBoldTextParagraph(it)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER)
                    .setFontSize(10f)
                    .setBorder(null)
                )
            }
        }
        listaParagraphConteudos.forEach {
            tabelaPrimaria.addCell(it)
        }

        document.add(tabelaPrimaria)
        document.close()
    }

    private fun createNoBorderCell(paragraph: Paragraph): Cell {
        return Cell().add(paragraph).setBorder(null)
    }

    private fun createBoldTextParagraph(text: String, color: Color = DeviceRgb.BLACK): Paragraph {
        val boldTextStyle = Style().apply {
            setFontSize(6f)
            setFontColor(color)
            setVerticalAlignment(VerticalAlignment.MIDDLE)
        }
        return Paragraph(text).addStyle(boldTextStyle)
    }

    private fun createProductTableCell(paragraph: Paragraph): Cell {
        return Cell().add(paragraph).apply {
            setPaddingBottom(10f)
            setPaddingTop(15f)
            setBorder(null)
            setBorderBottom(SolidBorder(DeviceRgb(204, 204, 204), 1f))
        }
    }

}
