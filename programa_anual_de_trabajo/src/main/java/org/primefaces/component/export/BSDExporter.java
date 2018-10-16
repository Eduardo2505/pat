package org.primefaces.component.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.util.Constants;

public class BSDExporter extends Exporter {
	
	@Override
    protected void exportAll(FacesContext context, DataTable table, Object document) {
        int first = table.getFirst();
    	int rowCount = table.getRowCount();
        int rows = table.getRows();
        boolean lazy = table.isLazy();
        
        if(lazy) {
            table.setFirst(0);
            table.setRows(rowCount);
            table.setRowIndex(-1);
            table.loadLazyData();
            for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                exportRow(table, document, rowIndex);
            }
            //restore
            table.setFirst(first);
            table.setRowIndex(-1);
            table.setRows(rows);
            table.loadLazyData();
        } 
        else {
            for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {                
                exportRow(table, document, rowIndex);
            }
            
            //restore
            table.setFirst(first);
        }
    }

    @Override
	public void export(FacesContext context, DataTable table, String filename, boolean pageOnly, boolean selectionOnly, String encodingType, MethodExpression preProcessor, MethodExpression postProcessor) throws IOException {    	
    	Workbook wb = new HSSFWorkbook();
    	Sheet sheet = wb.createSheet();
    	wb.setSheetName(0, " Rep. Pol�ticas Fiscalizadas");
        
    	if(preProcessor != null) {
    		preProcessor.invoke(context.getELContext(), new Object[]{wb});
    	}

        addColumnFacets(table, sheet, ColumnType.HEADER);
        
        if(pageOnly) {
            exportPageOnly(context, table, sheet);
        }
        else if(selectionOnly) {
            exportSelectionOnly(context, table, sheet);
        }
        else {
            exportAll(context, table, sheet);
        }
        
        if(table.hasFooterColumn()) {
            addColumnFacets(table, sheet, ColumnType.FOOTER);
        }
    	
    	table.setRowIndex(-1);
            	
    	if(postProcessor != null) {
    		postProcessor.invoke(context.getELContext(), new Object[]{wb});
    	}
    	
    	writeExcelToResponse(context.getExternalContext(), wb, filename);
	}
	
    @Override
    protected void exportCells(DataTable table, Object document) {
        Sheet sheet = (Sheet) document;
        int sheetRowIndex = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(sheetRowIndex);
        
        for(UIColumn col : table.getColumns()) {
            if(!col.isRendered()) {
                continue;
            }
            
            if(col instanceof DynamicColumn) {
                ((DynamicColumn) col).applyModel();
            }
            
            if(col.isExportable()) {
                addColumnValue(row, col.getChildren());
            }
        }
    }
    
	protected void addColumnFacets(DataTable table, Sheet sheet, ColumnType columnType) {
        int sheetRowIndex = columnType.equals(ColumnType.HEADER) ? 0 : (sheet.getLastRowNum() + 1);
        Row rowHeader = sheet.createRow(sheetRowIndex);
        
        for(UIColumn col : table.getColumns()) {
            if(!col.isRendered()) {
                continue;
            }
            
            if(col instanceof DynamicColumn) {
                ((DynamicColumn) col).applyModel();
            }
            
            if(col.isExportable()) {
                addColumnValue(rowHeader, col.getFacet(columnType.facet()));
            }
        }
    }
	
    protected void addColumnValue(Row row, UIComponent component) {
        int cellIndex = row.getLastCellNum() == -1 ? 0 : row.getLastCellNum();
        Cell cell = row.createCell(cellIndex);
        String value = component == null ? "" : exportValue(FacesContext.getCurrentInstance(), component);

        cell.setCellValue(new HSSFRichTextString(value));
    }
    
    protected void addColumnValue(Row row, List<UIComponent> components) {
        int cellIndex = row.getLastCellNum() == -1 ? 0 : row.getLastCellNum();
        Cell cell = row.createCell(cellIndex);
        StringBuilder builder = new StringBuilder();
        FacesContext context = FacesContext.getCurrentInstance();
        
        for(UIComponent component : components) {
        	if(component.isRendered()) {
                String value = exportValue(context, component);
                
                if(value != null)
                	builder.append(value);
            }
		}  
        
        cell.setCellValue(new HSSFRichTextString(builder.toString()));
    }
    
    protected void writeExcelToResponse(ExternalContext externalContext, Workbook generatedExcel, String filename) throws IOException {
    	externalContext.setResponseContentType("application/vnd.ms-excel");
    	externalContext.setResponseHeader("Expires", "0");
    	externalContext.setResponseHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
    	externalContext.setResponseHeader("Pragma", "public");
    	externalContext.setResponseHeader("Content-disposition", "attachment;filename="+ filename + ".xls");
    	externalContext.addResponseCookie(Constants.DOWNLOAD_COOKIE, "true", Collections.<String, Object>emptyMap());

        OutputStream out = externalContext.getResponseOutputStream();
        generatedExcel.write(out);
        externalContext.responseFlushBuffer();        
    }

	@Override
	public void export(FacesContext facesContext, List<String> clientIds,
			String outputFileName, boolean pageOnly, boolean selectionOnly,
			String encodingType, MethodExpression preProcessor,
			MethodExpression postProcessor) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void export(FacesContext facesContext, String outputFileName,
			List<DataTable> tables, boolean pageOnly, boolean selectionOnly,
			String encodingType, MethodExpression preProcessor,
			MethodExpression postProcessor) throws IOException {
		// TODO Auto-generated method stub
		
	}
}