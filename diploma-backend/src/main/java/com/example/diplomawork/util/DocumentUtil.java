package com.example.diplomawork.util;

import com.example.models.DefenceInfoByBlocksDto;
import com.example.models.UserDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentUtil {

    public static void generatePdf(DefenceInfoByBlocksDto dto, java.util.List<UserDto> commissions) throws IOException, DocumentException {
        Font timesNewRoman = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Font timesNewRomanBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("protocol.pdf"));

        Paragraph paragraph = new Paragraph("PROTOCOL", timesNewRomanBold);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        String defenceDate = "Defence date: " + dto.getDefence().getDefenceDate().toString();
        String commissionMembersHeader = "Members of the attestation commission: ";
        List commissionMembers = new List(List.ORDERED);
        commissions.stream().map(commission -> new ListItem(commission.getFirstName() + " " + commission.getLastName())).forEach(commissionMembers::add);

        String defenceLanguageTypeInfo = "Comprehensive exam language: English";
        String membersInfoHeader = "Examined students:";
        List members = new List(List.ORDERED);
        dto.getTeam().getMembers().stream().map(member -> new ListItem(member.getFirstName() + " " + member.getLastName())).forEach(members::add);

        List questions = new List(List.ORDERED);
        dto.getQuestions().stream().map(question -> new ListItem(question.getDescription() + " " + question.getGrade().toString() + "/100")).forEach(questions::add);

        document.open();
        document.add(paragraph);
        document.add(new Paragraph(defenceDate, timesNewRomanBold));
        document.add(new Paragraph(commissionMembersHeader, timesNewRoman));
        document.add(commissionMembers);
        document.add(new Paragraph(defenceLanguageTypeInfo, timesNewRoman));
        document.add(new Paragraph(membersInfoHeader, timesNewRoman));
        document.add(members);
        document.add(new Paragraph("Questions: ", timesNewRoman));
        document.add(questions);
        document.add(new Paragraph("Recognize that learners:", timesNewRoman));
        document.add(members);
        document.add(new Paragraph("passed the comprehensive exam", timesNewRoman));
        document.add(new Paragraph("Overall grade: ", timesNewRoman));
        document.add(new Paragraph(dto.getDefence().getGrade().toString() + "/100", timesNewRomanBold));
        document.close();
        writer.close();
    }
}
