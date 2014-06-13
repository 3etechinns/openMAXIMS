//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Daniel Laffan using IMS Development Environment (version 1.22 build 41215.900)
// Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.

package ims.nursing.forms.mrsaassessment;

import java.util.ArrayList;

import ims.domain.exceptions.StaleObjectException;
import ims.framework.Control;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.Date;
import ims.nursing.forms.mrsaassessment.GenForm.grdMRSAAssChildRow;
import ims.nursing.vo.MRSAAssessmentVoCollection;
import ims.nursing.vo.MRSAAssessmentVo;
import ims.nursing.vo.MRSASitesResultsVoCollection;
import ims.nursing.vo.MRSASitesResultsVo;
import ims.nursing.vo.lookups.MRSAResult;
import ims.core.vo.AuthoringInformationVo;
import ims.core.vo.CareContextShortVo;
import ims.core.vo.CareContextShortVoCollection;
import ims.core.vo.HcpFilter;
import ims.core.vo.HcpLiteVo;

public class Logic extends BaseLogic
{
	private static final int OPEN = 1;
	private static final int NEW = 2;
	private static final int DETAILVIEW = 3;
	private static final int READ = 4;
	private static final int HEADERVIEW = 6;
		
	protected void onFormOpen() throws ims.framework.exceptions.FormOpenException
	{
		initialize();
		open();
		
		updateMRSAMenu();
		updateMRSAChildMenu();
	}
	private void initialize()
	{
		loadContextRecords();
	}
	private void loadContextRecords()
	{
		form.getLocalContext().setgridIndex(new Integer(-1));
		CareContextShortVoCollection voCareContextList = domain.listMRSACareContexts(form.getGlobalContext().Core.getPatientShort().getID_Patient());
		if (voCareContextList != null && voCareContextList.size() > 0)
		{
			form.recbrAssessment().newRow(null,null); //empty row
			
			for (int i=0;i<voCareContextList.size();i++)
			{
				form.recbrAssessment().newRow(voCareContextList.get(i),voCareContextList.get(i).getStartDateTime().getDate().toString() + " " + voCareContextList.get(i).getStartDateTime().getTime().toString());
			}
		}

	}
	private void open() 
	{
		form.getLocalContext().setbNewHeaderRecord(Boolean.FALSE);
		form.getLocalContext().setbEditHeaderRecord(Boolean.FALSE);
		form.getLocalContext().setaddToExistingHeader(Boolean.FALSE);
		form.getLocalContext().setMRSAAssessment(null);
		form.setMode(FormMode.VIEW);
		
		setScreenMode(OPEN);
		
		form.recbrAssessment().setValue(form.getGlobalContext().Core.getCurrentCareContext());
		populateInstance(form.getGlobalContext().Core.getCurrentCareContext());
		
		form.grdMRSAAssChild().setReadOnly(true);
	}

	private void populateInstance(CareContextShortVo voCareContext)
	{
		clearDetails();
		MRSAAssessmentVoCollection voCollMRSA =  domain.listMRSA(voCareContext);	
		populateParentGrid(voCollMRSA);
		for(int i=0;i<form.grdMRSAAssChild().getRows().size(); i++)
			form.grdMRSAAssChild().getRows().get(i).setSelectable(false);		
	}	

	private void setScreenMode(int mode) 
	{
		switch (mode)
		{
			case OPEN:
				form.btnSave().setVisible(false);
				form.btnCancel().setVisible(false);
				form.dteScreening().setEnabled(false);
				form.btnNew().setVisible(true);	
				form.grdAssessment().setEnabled(true);
			break;
			
			case NEW:
				form.dteScreening().setEnabled(true);				
				form.btnSave().setVisible(true);
				form.btnNew().setVisible(false);
				form.btnCancel().setVisible(true);
				form.btnUpdate().setVisible(false);	
				form.grdAssessment().setEnabled(false);
				form.dteScreening().setFocus();	
				form.customControlAuth().initializeComponent();			
			break;
			
			case DETAILVIEW:
				form.dteScreening().setEnabled(false);	
				form.grdAssessment().setEnabled(true);			
				form.btnNew().setVisible(false);
				form.btnUpdate().setVisible(false);
				form.btnSave().setVisible(true);
				form.btnCancel().setVisible(true);
				if(form.getMode().equals(FormMode.EDIT))
					form.grdAssessment().setEnabled(false);
			break;

			case HEADERVIEW:
				form.dteScreening().setEnabled(false);
				form.btnSave().setVisible(true);
				form.btnCancel().setVisible(true);	
			break;
			
			case READ:
				form.dteScreening().setEnabled(false);
				form.btnSave().setVisible(false);
				form.btnCancel().setVisible(false);	
				form.btnNew().setVisible(false);
				form.btnUpdate().setVisible(false);
			break;
			
		}
	}
	private void clearHeader()
	{
		form.grdAssessment().getRows().clear();
		form.lblLastPosResult().setVisible(false);
	}
	private void populateParentGrid(MRSAAssessmentVoCollection voCollMRSA) 
	{ 
		
		clearHeader();
		
		MRSASitesResultsVo voLastRes = domain.getLastMrsaPosResult(form.getGlobalContext().Core.getPatientShort().getID_Patient());
		if ( voLastRes != null && voLastRes.getDateResultIsNotNull())
		{
			form.lblLastPosResult().setValue("Last positive result: " + voLastRes.getDateResult().toString());
			form.lblLastPosResult().setVisible(true);
		}
		else
			form.lblLastPosResult().setVisible(false);
		
		if(voCollMRSA == null)
			return;

		MRSAAssessmentVo voMRSA = null;
		MRSASitesResultsVo voSitesResult = null;
		MRSASitesResultsVoCollection voSitesResultColl = null;
		GenForm.grdAssessmentRow row = null;
		
		for(int i=0;i<voCollMRSA.size();i++)
		{
			voMRSA = voCollMRSA.get(i);
			row = form.grdAssessment().getRows().newRow();
			
			if(voMRSA.getDateScreening() != null)
				row.setColDateScreening(voMRSA.getDateScreening().toString());
				
			row.setValue(voMRSA);
			
			voSitesResultColl = voMRSA.getSitesAndResults();
			if(voSitesResultColl != null)
			{
				GenForm.grdAssessmentRow cRow = null;
				
				int posCount = 0;
				int negCount = 0;				
				
				for(int p=0;p<voSitesResultColl.size();p++)
				{
					voSitesResult = voSitesResultColl.get(p);
					cRow = row.getRows().newRow();
					
					if(voSitesResult.getOther() != null)
					{
						cRow.setColDateScreening(voSitesResult.getOther());
					}
					else if(voSitesResult.getSite() != null)
					{
						cRow.setColDateScreening(voSitesResult.getSite().getText());  
					}
						
					if(voSitesResult.getDateResult() != null)
					{
						cRow.setColDateResult(voSitesResult.getDateResult().toString());
					}	
					if(voSitesResult.getResult() != null)
					{
						cRow.setColResult(voSitesResult.getResult().getText());
						if(voSitesResult.getResult().equals(MRSAResult.POSITIVE))
							posCount++;
						if(voSitesResult.getResult().equals(MRSAResult.NEGATIVE))
							negCount++;
					}
							
					cRow.setValue(voSitesResult);
					cRow.setSelectable(false);
				}
				
				if(negCount == voSitesResultColl.size())
				{
					row.setTextColor(Color.Green);
					row.setColDateScreening(row.getColDateScreening() + " - Clear");
				}
				else if(posCount > 0)
				{
					row.setTextColor(Color.Red);
					row.setColDateScreening(row.getColDateScreening() + " - Positive");
				}

			}
		
		}
		
		//setting the first record to be selected
		if(voCollMRSA != null && voCollMRSA.size() > 0 && voCollMRSA.get(0) != null)
		{
			form.grdAssessment().setValue(voCollMRSA.get(0));
			onGrdAssessmentSelectionChanged();
		}
		
		expandFirstRow();

	}
	private void clearDetails() 
	{
		form.customControlAuth().setValue(null);			
		form.dteScreening().setValue(null);
		form.grdMRSAAssChild().getRows().clear();		
	}
	
	private MRSAAssessmentVo populateDataFromScreen() 
	{
		GenForm.grdAssessmentRow row = null;
		MRSAAssessmentVo voMRSA = null;
		MRSASitesResultsVo voSitesResult = null;
		
		form.getLocalContext().setaddToExistingHeader(Boolean.FALSE);
		
		//check through to see if there is already a header with this date
		GenForm.grdAssessmentRow dRow = null;
		for(int i=0;i<form.grdAssessment().getRows().size();i++)
		{
			dRow = form.grdAssessment().getRows().get(i);
			if(dRow.getColDateScreening() != null)
			{
				boolean bHcpUser = false;
				if(domain.getHcpUser() != null)
					bHcpUser = true;
					
				if(form.dteScreening().getValue() != null)
				{
					if(dRow.getColDateScreening().substring(0,10).equals(form.dteScreening().getValue().toString()) && bHcpUser && (((MRSAAssessmentVo)dRow.getValue()).getHcpInitiated().equals(domain.getHcpUser())))
					{
						//There is a header already with this date so add the new child to it, then update the record
						if(form.getLocalContext().getbNewHeaderRecord().equals(Boolean.TRUE))
						{
							form.grdAssessment().setValue(dRow.getValue());
							form.getLocalContext().setaddToExistingHeader(Boolean.TRUE);
							GenForm.grdMRSAAssChildRow childRow = null;
							
							voMRSA = (MRSAAssessmentVo) form.grdAssessment().getValue();	
							
							for(int z=0; z<form.grdMRSAAssChild().getRows().size(); z++)
							{
								childRow = form.grdMRSAAssChild().getRows().get(z);
								MRSASitesResultsVo voMRSASite = new MRSASitesResultsVo();
								voMRSASite.setSite(childRow.getcolSite());
								voMRSASite.setOther(null);
								
								voMRSASite.setResult(childRow.getcolResult());
								voMRSASite.setDateResult(childRow.getcolDateResult());
								if(voMRSA.getSitesAndResults() != null)
									voMRSA.getSitesAndResults().add(voMRSASite);
								else
								{
									MRSASitesResultsVoCollection voSiteResultColl = new MRSASitesResultsVoCollection();
									voSiteResultColl.add(voMRSASite);
									voMRSA.setSitesAndResults(voSiteResultColl);
								}	
							}
							form.getLocalContext().setbNewHeaderRecord(Boolean.FALSE);
							form.getLocalContext().setMRSAAssessment(voMRSA);
							setScreenMode(DETAILVIEW);
		
							expandFirstRow();
							break;
						}
					}
				}
			}
		}
		
		//new record
		if(form.getLocalContext().getbNewHeaderRecord().equals(Boolean.TRUE))
		{
			//add a header row
			row = form.grdAssessment().getRows().newRow();
			//form.customControlAuth().initializeComponent();			

			voMRSA = new MRSAAssessmentVo();
			voMRSA.setDateTimeInitiated(form.customControlAuth().getValue().getAuthoringDateTime());
			
			if(form.customControlAuth().getValue().getAuthoringHcpIsNotNull())
			{
				HcpLiteVo voHcpInitiated;
				HcpFilter filter = new HcpFilter();
				filter.setHcpType(form.customControlAuth().getValue().getAuthoringHcp().getHcpType());
				filter.setQueryName(form.customControlAuth().getValue().getAuthoringHcp().getName());
				filter.setID_Hcp(form.customControlAuth().getValue().getAuthoringHcp().getID_Hcp());
				voHcpInitiated = domain.getHcp(filter);
				voMRSA.setHcpInitiated(voHcpInitiated);
			}
			
			if(form.dteScreening().getValue()!=null){
				voMRSA.setDateScreening(form.dteScreening().getValue());
				row.setColDateScreening(form.dteScreening().getValue().toString());
			}
			row.setValue(voMRSA);
			voMRSA.setSitesAndResults(populateSitesResultsCollectionFromGrid());
			form.getLocalContext().setMRSAAssessment(voMRSA);
		}
		else //update
		{	
			
			voMRSA = form.getLocalContext().getMRSAAssessment();
			if(voMRSA == null)
			{
				engine.showMessage("No Record selected to update");
				return null;
			}
				
			row = form.grdAssessment().getRowByValue(voMRSA);
				
			if(row == null)
			{
				engine.showMessage("No Row selected to update");
				return null;
			}
				
			if(row.getParentRow() == null)
			{
				//if we are updating the header info
				((MRSAAssessmentVo)row.getValue()).setDateScreening(form.dteScreening().getValue());
				if(form.dteScreening().getValue() != null)
					row.setColDateScreening(form.dteScreening().getValue().toString());
				else
					row.setColDateScreening("");
	
					setScreenMode(HEADERVIEW);
					form.grdAssessment().setEnabled(true);
					//clearDetails();
					form.getLocalContext().setbEditHeaderRecord(Boolean.FALSE);
			}
			if(form.getLocalContext().getaddToExistingHeader().equals(Boolean.FALSE))
			{
				voMRSA.setSitesAndResults(populateSitesResultsCollectionFromGrid());
			}
		}
		
		clearDetails();
		setScreenMode(DETAILVIEW);
		
		//The following is used to default the parent as the selected row
		form.grdAssessment().setValue(voMRSA);
		onGrdAssessmentSelectionChanged();
	
		if(voSitesResult == null)	
		{
			//set all other parent rows non-selecteable
			for(int i=0;i<form.grdAssessment().getRows().size();i++)
			{
				if(!form.grdAssessment().getRows().get(i).getValue().equals(voMRSA))
					form.grdAssessment().getRows().get(i).setSelectable(false);
			}
		}
		
		form.getLocalContext().setbNewHeaderRecord(Boolean.FALSE);
		expandFirstRow();
	
		if(form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull())
			voMRSA.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
		if(form.getGlobalContext().Core.getCurrentCareContextIsNotNull())
			voMRSA.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());		
		
		return voMRSA;
	}

	/**
	 * @param sitesResultsVoColl
	 */
	private MRSASitesResultsVoCollection populateSitesResultsCollectionFromGrid()
	{
		GenForm.grdMRSAAssChildRow childRow = null;
		MRSASitesResultsVoCollection sitesResultsVoColl = new MRSASitesResultsVoCollection();
		for(int z=0; z<form.grdMRSAAssChild().getRows().size(); z++)
		{
			childRow = form.grdMRSAAssChild().getRows().get(z);
			MRSASitesResultsVo voMRSASite = childRow.getValue();
			voMRSASite.setSite(childRow.getcolSite());
						
			voMRSASite.setResult(childRow.getcolResult());
			voMRSASite.setDateResult(childRow.getcolDateResult());
			sitesResultsVoColl.add(voMRSASite);
		}

		return sitesResultsVoColl;
	}


	protected void onGrdAssessmentSelectionChanged() 
	{
		MRSAAssessmentVo voMRSA = null;
		GenForm.grdAssessmentRow row = null;
		form.grdMRSAAssChild().getRows().clear();		
		
		voMRSA = (MRSAAssessmentVo) form.grdAssessment().getValue();
		row = form.grdAssessment().getRowByValue(voMRSA);
	
		form.getLocalContext().setMRSAAssessment(voMRSA);  
		
		displayHeaderRecord((MRSAAssessmentVo)row.getValue()); //displaying parent
		
		MRSASitesResultsVoCollection voSiteColl = voMRSA.getSitesAndResults();
		form.grdMRSAAssChild().getRows().clear();
		if(voSiteColl != null)
		{
			for(int x=0;x<voSiteColl.size(); x++)
			{
				MRSASitesResultsVo voSite = voSiteColl.get(x);
				GenForm.grdMRSAAssChildRow childRow;
				if(voSite != null)
				{
					childRow = form.grdMRSAAssChild().getRows().newRow();
					childRow.setSelectable(false);
					childRow.setcolResult(voSite.getResult());
					childRow.setcolDateResult(voSite.getDateResult());
					if(voSite.getSiteIsNotNull())
						childRow.setcolSite(voSite.getSite());
					childRow.setValue(voSite);
				}
			}
		}
		updateMRSAMenu();
	}
	
	private void displayHeaderRecord(MRSAAssessmentVo voMRSA) 
	{
		AuthoringInformationVo voAuthoringInformation = new AuthoringInformationVo();
		voAuthoringInformation.setAuthoringDateTime(voMRSA.getDateTimeInitiated());
		if(voMRSA.getHcpInitiatedIsNotNull())
		{
			HcpLiteVo voHcpLite = new HcpLiteVo();
			voHcpLite.setMos(voMRSA.getHcpInitiated().getMos());
			voHcpLite.setHcpType(voMRSA.getHcpInitiated().getHcpType());
			voHcpLite.setID_Hcp(voMRSA.getHcpInitiated().getID_Hcp());
			voAuthoringInformation.setAuthoringHcp(voHcpLite);
		}
		form.customControlAuth().setValue(voAuthoringInformation);
		form.dteScreening().setValue(voMRSA.getDateScreening());
	}
	
	protected void onBtnUpdateClick() throws ims.framework.exceptions.PresentationLogicException
	{
		int index = form.grdAssessment().getSelectedRowIndex();
		if (index < 0)
		{
			engine.showMessage("Please select a record to update");
			return;
		}
		form.setcustomControlAuthEnabled(false);
		onGrdAssessmentSelectionChanged();
		updateMRSADetails();
		
	}
	private void updateMRSADetails()
	{
		form.setMode(FormMode.EDIT);
		
		setScreenMode(DETAILVIEW);
		form.dteScreening().setEnabled(true);
		
		
		
		GenForm.grdAssessmentRow row = form.grdAssessment().getRowByValue(form.grdAssessment().getValue());
		form.grdMRSAAssChild().setReadOnly(false);
		for(int i=0;i<form.grdMRSAAssChild().getRows().size();i++)
			form.grdMRSAAssChild().getRows().get(i).setSelectable(true);
	
		form.getLocalContext().setMRSAAssessment((MRSAAssessmentVo)row.getValue());
		form.getLocalContext().setbEditHeaderRecord(Boolean.FALSE);
		
		//set all other parent rows non-selecteable
	    for(int i=0;i<form.grdAssessment().getRows().size();i++)
	    {
		    if(!form.grdAssessment().getRows().get(i).getValue().equals(row.getValue()))
			    form.grdAssessment().getRows().get(i).setSelectable(false);
	    }
	    updateMRSAChildMenu();
	}
	
	
	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.setMode(FormMode.EDIT);
		clearDetails();
		setScreenMode(NEW);
		form.setcustomControlAuthEnabled(true);
		form.customControlAuth().initializeComponent();
		form.customControlAuth().setIsRequiredPropertyToControls(true);
		form.dteScreening().setValue(new Date());
		form.grdAssessment().setValue(null);
		form.grdMRSAAssChild().setReadOnly(false);
		updateMRSAMenu();
		form.getLocalContext().setbNewHeaderRecord(Boolean.TRUE);
	}

	protected void onBtnCancelClick() throws PresentationLogicException 
	{
		form.grdMRSAAssChild().setReadOnly(true);
		open();		
	}

	
	protected void onBtnSaveClick() throws PresentationLogicException 
	{
		String[] validationErrors = getUIErrors();
		if(validationErrors != null)
		{
			engine.showErrors(validationErrors);
			return;
		}
		
		MRSAAssessmentVo voMRSA = form.getLocalContext().getMRSAAssessment();
		if (voMRSA == null)
			voMRSA = new MRSAAssessmentVo();

		voMRSA = populateDataFromScreen();

	
		if (voMRSA != null)
		{
			String[] messages = voMRSA.validate();
			if (messages != null)
			{
				engine.showErrors("Validation Errors", messages);
				return;
			}
			
			try
			{
				domain.saveMRSA(voMRSA, form.getGlobalContext().Core.getCurrentClinicalContact());
			}
			catch (StaleObjectException e)
			{
				engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			}
			
			form.setMode(FormMode.VIEW);
			clearDetails();
			loadContextRecords();
			open();
		}
	}

	
	private String[] getUIErrors()
	{
		ArrayList<String> errors = new ArrayList<String>();
		if(form.customControlAuth().getValue()==null || form.customControlAuth().getValue().getAuthoringHcp() == null)
		{
			errors.add("Authoring HCP/Date/Time is mandatory.");
		}
		
		if(form.dteScreening().getValue()==null)
		{
			errors.add("Date Screening is mandatory.");
		}
		
		for (int i = 0; i < form.grdMRSAAssChild().getRows().size(); i++)
		{
			if(form.grdMRSAAssChild().getRows().get(i).getcolSite() == null)
			{
				errors.add("Site is mandatory.");
				break;
			}
		}
		
		if(errors.size() > 0)
		{
			String[] uiErrors = new String[errors.size()];
			errors.toArray(uiErrors);
			return uiErrors;
		}
		
		return null;
	}
	protected void onContextMenuItemClick(int menuItemID, Control sender) throws PresentationLogicException 
	{
		if(sender.equals(form.grdMRSAAssChild()))
		{
			switch(menuItemID)
			{
				case GenForm.ContextMenus.MedicationGrid.ADD:
					menuNew(sender);
					break;
				case GenForm.ContextMenus.MedicationGrid.REMOVE:
					menuRemove(sender);
					break;
			}
		}
		if(sender.equals(form.grdAssessment()))
		{
			switch(menuItemID)
			{
				case GenForm.ContextMenus.GenericGrid.Add:
					menuNew(sender);
					break;
				case GenForm.ContextMenus.GenericGrid.Update:
					menuEdit();
					break;
				case GenForm.ContextMenus.GenericGrid.Remove:
					menuRemove(sender);
					break;
			}
		}
		
		updateMRSAChildMenu();
	}

	
	private void menuNew(Control sender) 
	{
		if(sender.equals(form.grdMRSAAssChild()))
		{
			GenForm.grdMRSAAssChildRow cRow =  form.grdMRSAAssChild().getRows().newRow();
			cRow.setValue(new MRSASitesResultsVo());
		}
		else
		{
			form.setMode(FormMode.EDIT);
			//clearDetails();
			//onGrdAssessmentSelectionChanged();
			setScreenMode(NEW);
			form.dteScreening().setValue(new Date());
			form.setcustomControlAuthEnabled(true);
			form.customControlAuth().initializeComponent();
			//form.grdAssessment().setValue(null);
			form.getLocalContext().setbNewHeaderRecord(Boolean.FALSE);
			GenForm.grdMRSAAssChildRow cRow = form.grdMRSAAssChild().getRows().newRow();
			cRow.setValue(new MRSASitesResultsVo());
			form.grdMRSAAssChild().setReadOnly(false);
		}
	}
	private void updateMRSAMenu() 
	{
		form.getContextMenus().getGenericGridAddItem().setVisible(form.getMode().equals(FormMode.VIEW) && form.recbrAssessment().getValue() != null && form.recbrAssessment().getValue().equals(form.getGlobalContext().Core.getCurrentCareContext()));
		form.getContextMenus().getGenericGridUpdateItem().setVisible(form.getMode().equals(FormMode.VIEW) && form.grdAssessment().getSelectedRowIndex()>=0 && form.recbrAssessment().getValue() != null && form.recbrAssessment().getValue().equals(form.getGlobalContext().Core.getCurrentCareContext()));
		form.btnUpdate().setVisible(form.getMode().equals(FormMode.VIEW) && form.grdAssessment().getSelectedRowIndex()>=0 && (form.recbrAssessment().getValue() != null) && (form.recbrAssessment().getValue().equals(form.getGlobalContext().Core.getCurrentCareContext())));
		//form.getContextMenus().getGenericGridRemoveItem().setVisible(form.grdAssessment().getSelectedRowIndex()>=0 && form.grdAssessment().isEnabled());
		form.getContextMenus().getGenericGridRemoveItem().setVisible(false);
		form.getContextMenus().getGenericGridMoveDownItem().setVisible(false);
		form.getContextMenus().getGenericGridMoveUpItem().setVisible(false);
		form.getContextMenus().getGenericGridViewItem().setVisible(false);
	}
	private void updateMRSAChildMenu()
	{
		form.getContextMenus().getMedicationGridADDItem().setVisible(form.getMode().equals(FormMode.EDIT));
		form.getContextMenus().getMedicationGridREMOVEItem().setVisible(form.getMode().equals(FormMode.EDIT) && form.grdMRSAAssChild().getSelectedRowIndex()>=0);
	}
	private void menuEdit()
	{
		updateMRSADetails();
	}
	
	private void menuRemove(Control sender) 
	{
		if(sender.equals(form.grdMRSAAssChild()))
		{
			if(form.grdMRSAAssChild().getSelectedRowIndex() >= 0)
			{
				form.grdMRSAAssChild().removeSelectedRow();
				updateMRSAChildMenu();
			}
			else
			{
				engine.showMessage("Please select a record to be removed");
				return;
			}
			
		}
	}
	private void expandFirstRow() 
	{
		form.grdAssessment().collapseAll();
		for(int i=0;i<form.grdAssessment().getRows().size(); i++)
		{
			GenForm.grdAssessmentRow gRow = form.grdAssessment().getRows().get(i);
			if(i==0)
			{
				gRow.setExpanded(true);
				return;
			}
		}
	}
	
	protected void onGrdMRSAAssChildSelectionChanged() throws PresentationLogicException
	{
		updateMRSAChildMenu();
	}

	protected void onRecbrAssessmentValueChanged() throws PresentationLogicException
	{
		if (form.recbrAssessment().getValue() != null)
		{
			populateInstance(form.recbrAssessment().getValue());
			updateControlStatus();
		}
		else
		{
			open();
		}		
				
	}
	private void updateControlStatus()
	{
		if (form.recbrAssessment().getValue() == null)
		{
			setScreenMode(READ);
			form.recbrAssessment().setValue(form.getGlobalContext().Core.getCurrentCareContext());
		}
		else
		{
			if (form.recbrAssessment().getValue().equals(form.getGlobalContext().Core.getCurrentCareContext()))
			{
				setScreenMode(OPEN);
			}
			else
			{
				setScreenMode(READ);
				form.getContextMenus().getGenericGridAddItem().setVisible(false);
				form.getContextMenus().getGenericGridUpdateItem().setVisible(false);
				form.getContextMenus().getGenericGridRemoveItem().setVisible(false);
				form.getContextMenus().getGenericGridMoveDownItem().setVisible(false);
				form.getContextMenus().getGenericGridMoveUpItem().setVisible(false);
				form.getContextMenus().getGenericGridViewItem().setVisible(false);		
				form.getContextMenus().getMedicationGridADDItem().setVisible(false);
				form.getContextMenus().getMedicationGridREMOVEItem().setVisible(false);
				
			}
		}
	}
	protected void onGrdMRSAAssChildGridComboBoxSelectionChanged(int column, grdMRSAAssChildRow row, Object value) throws PresentationLogicException
	{
		if (row.getcolResult() != null && row.getcolDateResult() == null)
			row.setcolDateResult(new Date());
		else if (row.getcolResult() == null)
			row.setcolDateResult(null);
	}
	
	@Override
	protected void onFormModeChanged() 
	{
		updateMRSAMenu();
		updateMRSAChildMenu();
	}

}