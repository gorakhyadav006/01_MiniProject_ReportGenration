package com.insureance.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.insureance.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenrator {

	
	
	public Boolean genratePdf(List<CitizenPlan> plans, HttpServletResponse response) throws DocumentException, IOException {
		
		Document document= new Document(PageSize.A4);
		  PdfWriter.getInstance(document,response.getOutputStream());
		  document.open();
		  
		  Paragraph p = new Paragraph("Citizen Plan Information"); 
		  document.add(p);
		  
		  PdfPTable table = new PdfPTable(6);
		  table.addCell("ID");
		  table.addCell("Citizen Name");
		  table.addCell("Plan Name");
		  table.addCell("Plan Status");
		  table.addCell("Start Date ");
		  table.addCell("End Date");
		  
		  //List<CitizenPlan> plans = planRepo.findAll();
		  
		  for(CitizenPlan plan:plans) {
			  table.addCell(String.valueOf(plan.getCitizenId()));
			  table.addCell(plan.getCitizenName());
			  table.addCell(plan.getPlanName());
			  table.addCell(plan.getPlanStatus());
			  table.addCell(plan.getPlanStartDate()+"");
			  table.addCell(plan.getPlanEndDate()+"");  
		  }
		  document.add(table);
		  document.close();
		  
		return true;	
	}
}
