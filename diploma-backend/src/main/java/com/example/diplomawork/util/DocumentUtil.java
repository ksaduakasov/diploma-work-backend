package com.example.diplomawork.util;

import com.example.models.DefenceInfoByBlocksDto;
import com.example.models.UserDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentUtil {
    private static Long protocolNumber = 1L;

    public static void generatePdf(DefenceInfoByBlocksDto dto, java.util.List<UserDto> commissions) throws IOException, DocumentException {
        BaseFont newTimesRoman = BaseFont.createFont("C:\\Users\\ASUS\\IdeaProjects\\diploma-work\\diploma-work\\diploma-backend\\src\\main\\resources\\fonts\\timesnrcyrmt.ttf", "cp1251", BaseFont.EMBEDDED);
        Font font = new Font(newTimesRoman, 12, Font.NORMAL);
        Font boldFont = new Font(newTimesRoman, 12, Font.BOLD);

        Document protocol1 = new Document();
        PdfWriter writer = PdfWriter.getInstance(protocol1, new FileOutputStream("protocol1.pdf"));

        Paragraph title = new Paragraph("ПРОТОКОЛ № " + protocolNumber, font);
        title.setAlignment(Element.ALIGN_CENTER);

        Paragraph subTitle = new Paragraph("Заседания аттестационной комиссии", boldFont);
        subTitle.setAlignment(Element.ALIGN_CENTER);

        Paragraph date = new Paragraph("Дата " + dto.getDefence().getDefenceDate().toString(), boldFont);

        Paragraph attended = new Paragraph("Присутствовали:", boldFont);

        Paragraph headCommission = new Paragraph("Председатель аттестационной комиссии", font); //TODO Фамилия, имя отчество Председатель аттестационной комиссии

        Paragraph commissionList = new Paragraph("Члены аттестационной комиссии:", font);
        List commissionMembers = new List(List.ORDERED);
        commissions.stream().map(commission -> new ListItem(commission.getFirstName() + " " + commission.getLastName())).forEach(commissionMembers::add);

        Paragraph student = new Paragraph("По рассмотрению дипломной работы (проекта) обучающегося "
                + dto.getTeam().getMembers().get(0).getFirstName(), font); // TODO фамилия, имя, отчество обучающегося

        Paragraph eduProgram = new Paragraph("по образовательной программе ", font); // TODO наименование образовательной программы

        Paragraph topic = new Paragraph("на тему: " + dto.getTeam().getTeam().getTopic(), font);

        Paragraph language = new Paragraph("Язык дипломной работы (проекта): английский", font);

        Paragraph adviser = new Paragraph("Дипломная работа (проект) обучающегося выполнена под научным руководством\n"
                + dto.getTeam().getTeam().getAdvisor(), font); // TODO фамилия, имя, отчество, ученая или академическая степень, место работы, занимаемая должность

        Paragraph reviewer = new Paragraph("Рецензент фамилия, имя, отчество, ученая или академическая степень, место работы, занимаемая должность", font); //TODO Рецензент

        String listOfMaterials = "В аттестационную комиссию представлены следующие материалы:\n" +
                "1)\tтекст дипломной работы (проекта) на cтраницах;\n" +
                "2)\tотзыв научного руководителя с заключением «допускается к защите»\n" +
                "3)\tотзыв рецензента с оценкой указывается оценка рецензента\n" +
                "4)\tсправка о проверке дипломной работы (проекта) на наличии заимствовании\n";


        List listOfQuestions = new List(List.ORDERED);
        String questions = "Обучающемуся были заданы следующие вопросы: ";
        dto.getQuestions().stream().map(question -> new ListItem(question.getDescription() + " " + question.getGrade().toString() + "/100")).forEach(listOfQuestions::add);


        protocol1.open();
        protocol1.add(title);
        protocol1.add(subTitle);
        protocol1.add(date);
        protocol1.add(attended);
        protocol1.add(headCommission);
        protocol1.add(commissionList);
        protocol1.add(commissionMembers);
        protocol1.add(student);
        protocol1.add(eduProgram);
        protocol1.add(topic);
        protocol1.add(language);
        protocol1.add(adviser);
        protocol1.add(reviewer);
        protocol1.add(new Paragraph(listOfMaterials, font));
        protocol1.add(new Paragraph(questions, font));
        protocol1.add(listOfQuestions);
        protocol1.close();
        writer.close();


        Document protocol2 = new Document();
        PdfWriter writer2 = PdfWriter.getInstance(protocol1, new FileOutputStream("protocol2.pdf"));

        Paragraph studentFullName = new Paragraph("Обучающийся " + dto.getTeam().getMembers().get(0).getFirstName(), font);

        Paragraph passed = new Paragraph("сдал комплексный экзамен или защитил дипломную работу (проект)", font);

        Paragraph mark = new Paragraph("с оценкой:" + dto.getDefence().getGrade() + "\n" + dto.getDefence().getDefenceDate(), font);

        Paragraph giveDegree = new Paragraph("Присудить обучающемуся " + dto.getTeam().getMembers().get(0).getFirstName() + "\n" +
                "Степень бакалавр направление подготовки   по образовательной программе наименование образовательной программы\n", font);


        protocol2.open();
        protocol2.add(title);
        protocol2.add(subTitle);
        protocol2.add(date);
        protocol2.add(attended);
        protocol2.add(headCommission);
        protocol2.add(commissionList);
        protocol2.add(commissionMembers);
        protocol2.add(studentFullName);
        protocol2.add(eduProgram);
        protocol2.add(passed);
        protocol2.add(mark);
        protocol2.add(giveDegree);

        protocol2.close();
        writer2.close();

        protocolNumber++;
    }
}
