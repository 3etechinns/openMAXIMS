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
// This code was generated by Cornel Ventuneac using IMS Development Environment (version 1.71 build 3642.24101)
// Copyright (C) 1995-2010 IMS MAXIMS. All rights reserved.

package ims.clinical.forms.surgicalopnotespostopinstructionscc;

import ims.clinical.forms.edischargefutureplancomponent.GenForm;
import ims.clinical.forms.surgicalopnotespostopinstructionscc.GenForm.grdFollowUpRow;
import ims.clinical.vo.DischargeFuturePlanFollowUpVo;
import ims.clinical.vo.DischargeFuturePlanFollowUpVoCollection;
import ims.clinical.vo.DischargeFuturePlanVo;
import ims.clinical.vo.DischargeReportDetailVo;
import ims.clinical.vo.NurseEnabledInstructionsVo;
import ims.clinical.vo.SurgicalOperationNotesVo;
import ims.clinical.vo.SurgicalOperationSummaryVo;
import ims.clinicaladmin.vo.SurgicalOperationDetailsConfigVo;
import ims.configuration.AppRight;
import ims.configuration.gen.ConfigFlag;
import ims.core.resource.people.vo.HcpRefVo;
import ims.core.resource.people.vo.MemberOfStaffRefVo;
import ims.core.vo.AuthoringInformationVo;
import ims.core.vo.CareContextShortVo;
import ims.core.vo.HcpFilter;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.HcpLiteVoCollection;
import ims.core.vo.LocationLiteVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.PatientDocumentVo;
import ims.core.vo.PersonName;
import ims.core.vo.ServerDocumentVo;
import ims.core.vo.enums.MosType;
import ims.core.vo.lookups.DocumentCategory;
import ims.core.vo.lookups.DocumentCreationType;
import ims.core.vo.lookups.FileType;
import ims.core.vo.lookups.HcpDisType;
import ims.core.vo.lookups.PreActiveActiveInactiveStatus;
import ims.core.vo.lookups.YesNo;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.enumerations.FormMode;
import ims.framework.enumerations.SystemLogLevel;
import ims.framework.enumerations.SystemLogType;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.DateTime;
import ims.vo.interfaces.IMos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ims.query.builder.client.QueryBuilderClient;
import com.ims.query.builder.client.SeedValue;
import com.ims.query.builder.client.exceptions.QueryBuilderClientException;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;
	private String urlQueryServer = ConfigFlag.GEN.QUERY_SERVER_URL.getValue();
	private String urlReportServer = ConfigFlag.GEN.REPORT_SERVER_URL.getValue();
	private static final Logger		LOG		= Logger.getLogger(Logic.class);

	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialise();
		//refresh();
	}

	protected void onContextMenuItemClick(int menuItemID, ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
	{
		switch (menuItemID)
		{
			case GenForm.ContextMenus.GenericGrid.Add:
				newFuturePlan();
				break;
			case GenForm.ContextMenus.GenericGrid.Update:
				updateFuturePlan();
				break;
		}
	}
	private void updateFuturePlan()
	{
		
	}

	private void newFuturePlan()
	{
		
		form.grdFollowUp().getRows().newRow();
	}
	public void open() throws ims.framework.exceptions.PresentationLogicException
	{
		///form.getLocalContext().setcurentSurgicalOpNotes(domain.getSurgicalOperation(form.getGlobalContext().Core.getCurrentCareContext())); 
		//form.getLocalContext().setEditedRecord(domain.getDischargeReportDetailsForCareContext(form.getGlobalContext().Core.getCurrentCareContext()));
		DischargeFuturePlanVo voFuture = domain.getFuturePlan(form.getGlobalContext().Core.getCurrentCareContext());
		if(voFuture != null)
		{
			
			if(voFuture.getLinkedSurgicalOperationNoteIsNotNull())
				form.getLocalContext().setcurentSurgicalOpNotes(voFuture.getLinkedSurgicalOperationNote());
			else
			{
				SurgicalOperationNotesVo voSO =  domain.getSurgicalOperation(form.getGlobalContext().Core.getCurrentCareContext());
				form.getLocalContext().setcurentSurgicalOpNotes(voSO);
			}
			form.getLocalContext().setselectedRecord(voFuture);
		}
		
		populateScreenFromData(voFuture);
		if (voFuture == null)
		{
			NurseEnabledInstructionsVo voNEI = domain.getNurseEnabledInstructions(form.getGlobalContext().Core.getCurrentCareContext());
			if (voNEI != null)
			{
				voFuture = new DischargeFuturePlanVo();
				voFuture.setNurseEnabledInstructions(voNEI);
			}
			SurgicalOperationNotesVo voSO =  domain.getSurgicalOperation(form.getGlobalContext().Core.getCurrentCareContext());
			if(voSO != null )
			{
				if(voFuture == null)
					voFuture = new DischargeFuturePlanVo();
				
				voFuture.setLinkedSurgicalOperationNote(voSO);
				
			}
			if(voFuture != null)
			{
				form.getLocalContext().setcurentSurgicalOpNotes(voFuture.getLinkedSurgicalOperationNote());
				form.getLocalContext().setselectedRecord(voFuture);
			}
			if(voFuture != null && voFuture.getNurseEnabledInstructions() != null)
				form.txtNurseEnabled().setValue(voFuture.getNurseEnabledInstructions().getNurseEnabledInstructions());
		}
		form.setMode(FormMode.VIEW);
		updateControlsState();
		updateContextMenus();
		
		
	}
	private void populateScreenFromData(DischargeFuturePlanVo voFuture)
	{
		clearInstanceControls();

		if (voFuture == null)
			return;

		form.grdFollowUp().getRows().clear();
		
		//WDEV-15862
		for (int i = 0; voFuture.getOpNotesFollowUpDetailsIsNotNull() && i < voFuture.getOpNotesFollowUpDetails().size(); i++)
		{
			grdFollowUpRow row = form.grdFollowUp().getRows().newRow();
			
			row.setColFollowUpType(voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpTypeIsNotNull() ? voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpType() : null);
			row.setColHospFollowUp(voFuture.getOpNotesFollowUpDetails().get(i).getHospitalFollowUpIsNotNull() ? voFuture.getOpNotesFollowUpDetails().get(i).getHospitalFollowUp() : null);
			row.setColIN(voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpInValueIsNotNull() ? voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpInValue() : null);
			row.setColPeriod(voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpInUnitIsNotNull() ? voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpInUnit() : null);
			row.getColHCP().clear();
			if (voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpHCPIsNotNull())
			{
				row.getColHCP().newRow(voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpHCP(), voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpHCP().getName().toString());
				row.getColHCP().setValue(voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpHCP());
			}
			row.setColSpec(voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpSpecialtyIsNotNull() ? voFuture.getOpNotesFollowUpDetails().get(i).getFollowUpSpecialty() : null);
			row.getColLoc().clear();
			if (voFuture.getOpNotesFollowUpDetails().get(i).getLocationIsNotNull())
			{
				row.getColLoc().newRow(voFuture.getOpNotesFollowUpDetails().get(i).getLocation(), voFuture.getOpNotesFollowUpDetails().get(i).getLocation().getName().toString());
				row.getColLoc().setValue(voFuture.getOpNotesFollowUpDetails().get(i).getLocation());
			}

			row.setValue(voFuture.getOpNotesFollowUpDetails().get(i));
			
			//wdev-9379
			verifyAndDisableNonHospFollowUp(row);
		}
		
		
		form.txtGeneral().setValue(voFuture.getComments());
		
		//WDEV-15862
		//form.txtNurseEnabled().setValue(voFuture.getNurseEnabledInstructionsIsNotNull() ? voFuture.getNurseEnabledInstructions().getNurseEnabledInstructions() : "");
		//form.txtActions().setValue(voFuture.getActionsforGPandCommunityIsNotNull() ? voFuture.getActionsforGPandCommunity() : null);
		
		form.txtNurseEnabled().setValue(voFuture.getOpNotesNurseEnabledInstructionsIsNotNull() ? voFuture.getOpNotesNurseEnabledInstructions().getNurseEnabledInstructions() : "");
		form.txtActions().setValue(voFuture.getOpNotesActionsforGPandCommunityIsNotNull() ? voFuture.getOpNotesActionsforGPandCommunity() : null);
		
		if(form.getLocalContext().getcurentSurgicalOpNotes() != null)
		{
			form.ccCompletingClinician().setValue(form.getLocalContext().getcurentSurgicalOpNotes().getSignOffHCP());
			form.dtimCompletion().setValue(form.getLocalContext().getcurentSurgicalOpNotes().getSignOffDateTime());
		}
		//wdev-13658
		form.chkSurgicalOperationNotesComplete().setValue(voFuture.getIsSugicalOpNotesCompleteIsNotNull() ? voFuture.getIsSugicalOpNotesComplete():Boolean.FALSE);
		setHideVisbleSIgnOffControls(Boolean.FALSE);
		//-----------------
		
		
		
	}
		
		
		
	public void clearInstanceControls()
	{
		//form.GroupFollowUp().setValue(GroupFollowUpEnumeration.None);

		//form.grdResults().getRows().clear();
		
		form.grdFollowUp().getRows().clear();
		form.txtNurseEnabled().setValue(null);
		form.txtGeneral().setValue(null);
		form.txtActions().setValue(null);
		form.ccCompletingClinician().setValue(null);
		form.dtimCompletion().setValue(null);
		form.chkSurgicalOperationNotesComplete().setValue(false);//WDEV-14727
	}
	
	public void newInstance() throws ims.framework.exceptions.PresentationLogicException
	{
		// TODO: Add you code here.
	}

	public boolean save() throws ims.framework.exceptions.PresentationLogicException
	{
		//dummyDishargeFPSurgicalOpNotesVo dummyVo = new dummyDishargeFPSurgicalOpNotesVo();
		DischargeFuturePlanVo voFuture = populateDataFromScreen();
		String[] errors = null;
		String[] str = voFuture.validate(validateUIRules(errors));
		if (str != null && str.length > 0)
		{
			engine.showErrors(str);
			return false;
		}

		try
		{
			voFuture = domain.save(voFuture, form.getLocalContext().getSurgicalOperationSummary());			//wdev-15917
		}
		catch (DomainInterfaceException e)
		{
			engine.showMessage(e.getMessage());
			return false;
		}
		catch (StaleObjectException e)
		{
			engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			
			form.fireCustomControlValueChanged();//wdev-9779

			open();
			return false;
		}
		form.getLocalContext().setselectedRecord(voFuture);
		form.getLocalContext().setcurentSurgicalOpNotes(voFuture.getLinkedSurgicalOperationNote());
		form.fireCustomControlValueChanged();//wdev-13658
		return true;
	}
	//wdev-13658
	private String[] validateUIRules(String[] existingErrors)
	{
		ArrayList<String> listOfErrors = new ArrayList<String>();
		if (existingErrors != null)
		{
			Collections.addAll(listOfErrors,existingErrors);
		}
		if(Boolean.TRUE.equals(form.chkSurgicalOperationNotesComplete().getValue()))
		{
			if(form.ccCompletingClinician().getValue() == null || form.dtimCompletion().getValue() == null)
			{
				listOfErrors.add("Completing Clinician and Date of Completion are mandatory.");
			}
		}
		
				
		if (listOfErrors.size() == 0 )
			return null;
		String[] returningErrors = new String[listOfErrors.size()];
		listOfErrors.toArray(returningErrors);
		return returningErrors;
	}
	private SurgicalOperationNotesVo populateDataFromScreenForSurgical()
	{
		SurgicalOperationNotesVo voSurgical = null;
		if(form.getLocalContext().getcurentSurgicalOpNotesIsNotNull())
			voSurgical = form.getLocalContext().getcurentSurgicalOpNotes();
		else
			voSurgical = new SurgicalOperationNotesVo();
		
		if (voSurgical.getCareContext() == null)
			voSurgical.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
		SurgicalOperationSummaryVo tempSurgOpSummVo = form.getLocalContext().getSurgicalOperationSummary(); //wdev-15917
		
		if(Boolean.TRUE.equals(form.chkSurgicalOperationNotesComplete().getValue()))    //wdev-13658
		{
			voSurgical.setSignOffDateTime(form.dtimCompletion().getValue());
		
			IMos hcp = form.ccCompletingClinician().getValue();
			voSurgical.setSignOffHCP(hcp == null ? null : domain.getHCP((HcpRefVo) hcp) );
		}
		else
		{
			voSurgical.setSignOffDateTime(null);  	//wdev-13658
			voSurgical.setSignOffHCP(null);			//wdev-13658
			voSurgical.setSurgicalOpNoteDocument(null); //wdev-14074
			
			if( tempSurgOpSummVo != null)			//wdev-15917
				tempSurgOpSummVo.setSummaryDocumentGenerated(Boolean.FALSE);
			
		}
		form.getLocalContext().setSurgicalOperationSummary(tempSurgOpSummVo); 	//wdev-15917
		
//		voSurgical.setSignOffHCP((HcpLiteVo)form.ccCompletingClinician().getValue());
		
		//if fields from "Sign Off" (i.e Completing Clinical and Date of Completion ) are populated then report can be build and save
		//generateDocument(voSurgical);
		if(voSurgical.getAuthoringInfo() == null)
		{
			HcpLiteVo temp = (HcpLiteVo) domain.getHcpLiteUser();
			voSurgical.setAuthoringInfo(new AuthoringInformationVo()); 
			voSurgical.getAuthoringInfo().setAuthoringHcp(temp);
			voSurgical.getAuthoringInfo().setAuthoringDateTime(new DateTime());
		}
		return voSurgical;
	}
	private boolean saveGeneratedDocument() throws PresentationLogicException
	{
		ArrayList<String> errors = new ArrayList<String>();
		DischargeFuturePlanVo sonVo = generateDocumentAndPopulate(form.getLocalContext().getselectedRecord(), errors);
		
		if (sonVo == null)
		{
    		if (errors.size() == 0 )
    		{
    			errors.add("An unknown error has occured while generating the document");
    		}
    		
		}
		else
		{
			
			String[] validateErrors = sonVo.validate(getErrorsFromList(errors));
			if (validateErrors != null && validateErrors.length>0)
				errors = (ArrayList<String>) Arrays.asList(validateErrors);
		}
		if (errors.size() > 0)
		{
			engine.showErrors(getErrorsFromList(errors));
			return false;
		}
		try
		{
			SurgicalOperationSummaryVo tempSurgOpSummVo = form.getLocalContext().getSurgicalOperationSummary(); //wdev-15917
			if( tempSurgOpSummVo != null) 	//wdev-15917
				tempSurgOpSummVo.setSummaryDocumentGenerated(Boolean.TRUE);
			form.getLocalContext().setSurgicalOperationSummary(tempSurgOpSummVo);								//wdev-15917
			
			form.getLocalContext().setselectedRecord(domain.save(sonVo,form.getLocalContext().getSurgicalOperationSummary()));	//wdev-15917
			form.getLocalContext().setcurentSurgicalOpNotes(form.getLocalContext().getselectedRecord().getLinkedSurgicalOperationNote());
		}
		catch (DomainInterfaceException e)
		{
			engine.showErrors(new String[]{e.getMessage()});
			return false;
		}
		catch (StaleObjectException e)
		{
			engine.showErrors(new String[]{ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue()});
			open();
			return false;
		}
		return true;
	}

	private DischargeFuturePlanVo generateDocumentAndPopulate(DischargeFuturePlanVo dischargeFuturePlanVo,ArrayList<String> errors) throws PresentationLogicException
	{
		if (dischargeFuturePlanVo == null || !dischargeFuturePlanVo.getID_FuturePlanIsNotNull() ||
				!dischargeFuturePlanVo.getLinkedSurgicalOperationNoteIsNotNull() || !dischargeFuturePlanVo.getLinkedSurgicalOperationNote().getID_SurgicalOperationNotesIsNotNull())
		{
			throw new PresentationLogicException("Logic Error - a saved valid SurgicalOperationNotes VO was expected!");
		}
		
		//Try to generate the report
		if (errors == null)
			errors = new ArrayList<String>();
		String filename = buildReport(errors);
		if (filename == null)
			return null;
		

		//String patientDocumentName = "Report Notes";
		//DocumentCategory patientDocumentCategory = DocumentCategory.DISCHARGE_REPORTS;
		
		//wdev-15920
		String patientDocumentName = "eOpNotes Report";
		DocumentCategory patientDocumentCategory = DocumentCategory.OPNOTES_REPORTS;
		
			
		if (dischargeFuturePlanVo.getLinkedSurgicalOperationNote().getSurgicalOpNoteDocumentIsNotNull())
			throw new PresentationLogicException("Logic Error - Surgical OP Notes Document already generated");
		
		dischargeFuturePlanVo.getLinkedSurgicalOperationNote().setSurgicalOpNoteDocument(populatePatientDocumentVo(populateServetDocumentVo(filename),patientDocumentName, patientDocumentCategory));
		
		return dischargeFuturePlanVo;
	}
	private ServerDocumentVo populateServetDocumentVo(String fileName) 
	{
		DateTime date = new DateTime();
		int year = date.getDate().getYear();
		int month = date.getDate().getMonth();
		int day = date.getDate().getDay();
		
		ServerDocumentVo vo = new ServerDocumentVo();				
		String filePath = year + "/" + month + "/" + day + "/" + fileName;			
		vo.setFileName(filePath);
		vo.setFileType(FileType.PDF);		
		return vo;		
	}	
	
	private PatientDocumentVo populatePatientDocumentVo(ServerDocumentVo serverDocumentVo, String patientDocumentName, DocumentCategory patientDocumentCategory)
	{
		PatientDocumentVo vo = new PatientDocumentVo();
		vo.setPatient(form.getGlobalContext().Core.getPatientShort());
		vo.setEpisodeofCare(form.getGlobalContext().Core.getEpisodeofCareShort());
		vo.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
		vo.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
		vo.setReferral(null);
				
		vo.setName(patientDocumentName);
		vo.setServerDocument(serverDocumentVo);
		vo.setCreationType(DocumentCreationType.GENERATED);
		vo.setCategory(patientDocumentCategory);
		
		vo.setRecordingUser((MemberOfStaffRefVo)domain.getMosUser(engine.getLoggedInUser().getUsername()));
		vo.setRecordingDateTime(new DateTime());
		vo.setStatus(PreActiveActiveInactiveStatus.ACTIVE);
		//wdev-14083
		if(form.getGlobalContext().Core.getEpisodeofCareShortIsNotNull())
		{
			vo.setSpecialty(form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialty());
			vo.setResponsibleHCP(form.getGlobalContext().Core.getEpisodeofCareShort().getResponsibleHCP());
		}
		
		if(form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull())  //clinicalcontact
		{
			DateTime datetTime = form.getGlobalContext().Core.getCurrentClinicalContact().getStartDateTime();
			vo.setDocumentDate(datetTime.getDate());
		}
		else if(form.getGlobalContext().Core.getCurrentCareContextIsNotNull())  //carecontext
		{
			DateTime datetTime = form.getGlobalContext().Core.getCurrentCareContext().getStartDateTime();
			vo.setDocumentDate(datetTime.getDate());
		}
		else if(form.getGlobalContext().Core.getEpisodeofCareShortIsNotNull())	//episodeofcare
		{
			vo.setDocumentDate(form.getGlobalContext().Core.getEpisodeofCareShort().getStartDate());
		}
		else
		{
			vo.setDocumentDate(new ims.framework.utils.Date());
		}
		//----------
		return vo;
	}
	private DischargeFuturePlanVo populateDataFromScreen()
	{
		DischargeFuturePlanVo voFuture = null;
		if (form.getLocalContext().getselectedRecordIsNotNull())
			voFuture = form.getLocalContext().getselectedRecord();
		else
			voFuture = new DischargeFuturePlanVo();

		if (voFuture.getCareContext() == null)
			voFuture.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());

		//WDEV-15862
		if (domain.getDischargeDetails(form.getGlobalContext().Core.getCurrentCareContext())==null)
		{
			voFuture = populateFollowDetails(voFuture);
		
    		voFuture.setActionsforGPandCommunity(form.txtActions().getValue());
    		NurseEnabledInstructionsVo voDisch = null;
    		if (form.getLocalContext().getselectedRecordIsNotNull() && voFuture.getNurseEnabledInstructionsIsNotNull())
    			voDisch = form.getLocalContext().getselectedRecord().getNurseEnabledInstructions();
    		else
    			voDisch = new NurseEnabledInstructionsVo();
    		
    		if (voDisch != null)
    		{		
    			voDisch.setNurseEnabledInstructions(form.txtNurseEnabled().getValue());
    			voDisch.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
    			voFuture.setNurseEnabledInstructions(voDisch);
    		}
		}
		
		voFuture.setComments(form.txtGeneral().getValue());
		
		SurgicalOperationSummaryVo tempSurgOpSummVo = form.getLocalContext().getSurgicalOperationSummary(); //wdev-15719
		//wdev-13658
		if(Boolean.TRUE.equals(form.chkSurgicalOperationNotesComplete().getValue()))
		{
			voFuture.setIsSugicalOpNotesComplete(Boolean.TRUE);
			if( tempSurgOpSummVo != null ) //wdev-15917
				tempSurgOpSummVo.setOperationDetailsCompleted(Boolean.TRUE); 
		}
		else
		{
			voFuture.setIsSugicalOpNotesComplete(Boolean.FALSE);
			if( tempSurgOpSummVo != null ) //wdev-15917
				tempSurgOpSummVo.setOperationDetailsCompleted(Boolean.FALSE);
		}
		//-----------
		
		form.getLocalContext().setSurgicalOperationSummary(tempSurgOpSummVo); //wdev-15917
		
		SurgicalOperationNotesVo voSurg = null;
		if(voFuture.getLinkedSurgicalOperationNote() != null)
			voSurg = voFuture.getLinkedSurgicalOperationNote();
		voSurg = populateDataFromScreenForSurgical();	
		voFuture.setLinkedSurgicalOperationNote(voSurg);
		
		
		
		//WDEV-15862
		
		voFuture.setOpNotesFollowUpDetails(populateFollowUpDetailsDataFromScreen());
		voFuture.setOpNotesActionsforGPandCommunity(form.txtActions().getValue());
		
		NurseEnabledInstructionsVo voOpNotesNurseEnabled =voFuture.getOpNotesNurseEnabledInstructions();
		if (voOpNotesNurseEnabled==null)
			voOpNotesNurseEnabled=new NurseEnabledInstructionsVo();
		if (voOpNotesNurseEnabled != null)
		{		
			voOpNotesNurseEnabled.setNurseEnabledInstructions(form.txtNurseEnabled().getValue());
			voOpNotesNurseEnabled.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
			voFuture.setOpNotesNurseEnabledInstructions(voOpNotesNurseEnabled);
		}
		
		return voFuture;
	}
	
	//WDEV-15862
	private DischargeFuturePlanFollowUpVoCollection populateFollowUpDetailsDataFromScreen()
	{
		DischargeFuturePlanFollowUpVoCollection voColl = new DischargeFuturePlanFollowUpVoCollection();
		for (int i = 0; i < form.grdFollowUp().getRows().size(); i++)
		{
			DischargeFuturePlanFollowUpVo voFutureFOllowup = new DischargeFuturePlanFollowUpVo();
			if (form.grdFollowUp().getRows().get(i).getValue() != null)
				{
					voFutureFOllowup = (DischargeFuturePlanFollowUpVo)form.grdFollowUp().getRows().get(i).getValue().clone();
					voFutureFOllowup.setID_FuturePlanFollowUp(null);
				}
			
			voFutureFOllowup.setFollowUpType(form.grdFollowUp().getRows().get(i).getColFollowUpType());
			voFutureFOllowup.setHospitalFollowUp(form.grdFollowUp().getRows().get(i).getColHospFollowUp());
			voFutureFOllowup.setFollowUpInValue(form.grdFollowUp().getRows().get(i).getColIN());
			voFutureFOllowup.setFollowUpInUnit(form.grdFollowUp().getRows().get(i).getColPeriod());
			voFutureFOllowup.setFollowUpHCP((HcpLiteVo) form.grdFollowUp().getRows().get(i).getColHCP().getValue());
			voFutureFOllowup.setFollowUpSpecialty(form.grdFollowUp().getRows().get(i).getColSpec());
			voFutureFOllowup.setLocation((LocationLiteVo) form.grdFollowUp().getRows().get(i).getColLoc().getValue());

			voColl.add(voFutureFOllowup);
		}
		return voColl;
	}

	private DischargeFuturePlanVo populateFollowDetails(DischargeFuturePlanVo voFuture)
	{
		DischargeFuturePlanFollowUpVoCollection voColl = new DischargeFuturePlanFollowUpVoCollection();
		for (int i = 0; i < form.grdFollowUp().getRows().size(); i++)
		{
			DischargeFuturePlanFollowUpVo voFutureFOllowup = new DischargeFuturePlanFollowUpVo();
			if (form.grdFollowUp().getRows().get(i).getValue() != null)
				voFutureFOllowup = form.grdFollowUp().getRows().get(i).getValue();

			voFutureFOllowup.setFollowUpType(form.grdFollowUp().getRows().get(i).getColFollowUpType());
			voFutureFOllowup.setHospitalFollowUp(form.grdFollowUp().getRows().get(i).getColHospFollowUp());
			voFutureFOllowup.setFollowUpInValue(form.grdFollowUp().getRows().get(i).getColIN());
			voFutureFOllowup.setFollowUpInUnit(form.grdFollowUp().getRows().get(i).getColPeriod());
			voFutureFOllowup.setFollowUpHCP((HcpLiteVo) form.grdFollowUp().getRows().get(i).getColHCP().getValue());
			voFutureFOllowup.setFollowUpSpecialty(form.grdFollowUp().getRows().get(i).getColSpec());
			voFutureFOllowup.setLocation((LocationLiteVo) form.grdFollowUp().getRows().get(i).getColLoc().getValue());

			voColl.add(voFutureFOllowup);
		}
		voFuture.setFollowUpDetails(voColl);
		return voFuture;
	}
	public void updateControlsState()
	{
		if(form.getMode().equals(FormMode.EDIT))
		{	
			//WDEV-15892
			boolean eDischargeExist;
			if (domain.getDischargeDetails(form.getGlobalContext().Core.getCurrentCareContext())==null)
				eDischargeExist=false;
			else
				eDischargeExist=true;
				
			form.txtActions().setEnabled(!eDischargeExist);
			form.txtNurseEnabled().setEnabled(!eDischargeExist);
			form.grdFollowUp().setEnabled(!eDischargeExist);
			form.grdFollowUp().setReadOnly(eDischargeExist);
			if (eDischargeExist)
			{
				form.getContextMenus().hideAllGenericGridMenuItems();
			}
			
			form.txtGeneral().setEnabled(true);
			form.ccCompletingClinician().setEnabled(true);	//wdev-13658
			form.dtimCompletion().setEnabled(true);			//wdev-13658
		}
		else
		{
			
			boolean isClinicalCompleted = false;
			boolean isDocumentGenerated = false; 
			
			if (form.getLocalContext().getcurentSurgicalOpNotesIsNotNull())
			{
				SurgicalOperationNotesVo getcurentSurgicalOpNotes = form.getLocalContext().getcurentSurgicalOpNotes();
				isClinicalCompleted = getcurentSurgicalOpNotes.getSignOffDateTimeIsNotNull() && 
										getcurentSurgicalOpNotes.getSignOffHCPIsNotNull();
				isDocumentGenerated = getcurentSurgicalOpNotes.getSurgicalOpNoteDocumentIsNotNull();
			}
			
			form.btnGenerate().setVisible(isClinicalCompleted && !isDocumentGenerated);
			
			if(form.getLocalContext().getReadOnly() != null )
			{
				if(form.getLocalContext().getReadOnly() == false)
				{
					form.btnEdit().setEnabled(true);
					form.btnEdit().setVisible(true);
				}
				else
				{
					form.btnEdit().setVisible(true);
					form.btnEdit().setEnabled(false);
				}
			}
			else
			{
				form.btnEdit().setEnabled(true);
				form.btnEdit().setVisible(true);
			}
				
			form.ccCompletingClinician().setEnabled(false); //wdev-13658
			form.dtimCompletion().setEnabled(false);		//wdev-13658
			
		}
	}
	public void updateInstance()
	{
		// TODO: Add you code here.
	}

	
	protected void onBtnEditClick() throws PresentationLogicException 
	{
		form.getLocalContext().setSurgicalOperationSummary(domain.getSurgicalOperationSUmmary(form.getGlobalContext().Core.getCurrentCareContext())); //wdev-15917 
		DischargeReportDetailVo tempVo = domain.getDischargeReportDetailsForCareContext(form.getGlobalContext().Core.getCurrentCareContext());
		form.setMode(FormMode.EDIT);
		updateContextMenus();
		updateControlsState();
		//wdev-13658
		if(!form.getLocalContext().getselectedRecordIsNotNull() || (form.getLocalContext().getselectedRecordIsNotNull() && form.getLocalContext().getselectedRecord().getID_FuturePlan() == null && !form.getLocalContext().getselectedRecord().getNurseEnabledInstructionsIsNotNull() ))
		{
			SurgicalOperationNotesVo opNotes = domain.getOperationNotes(form.getGlobalContext().Core.getCurrentCareContext());
			if (opNotes !=null && opNotes.getMainProcedureIsNotNull() )
				try
				{
					SurgicalOperationDetailsConfigVo tempSurVo = domain.getDefaultTemplate(opNotes.getMainProcedure());
					if(tempSurVo != null)
					{
						form.txtActions().setValue("");
						form.txtGeneral().setValue("");
						form.txtNurseEnabled().setValue("");
						form.txtActions().setValue(tempSurVo.getActionsforGP());
						form.txtGeneral().setValue(tempSurVo.getHospitalPlan());
						form.txtNurseEnabled().setValue(tempSurVo.getNurseEnabledInstructions());
					}
					
				}
				catch (DomainInterfaceException e)
				{
					throw new PresentationLogicException(e);
				}
		}
		//----------
		if(tempVo != null)
		{
			if(tempVo.getIsClinicalDetailsComplete() == true && Boolean.FALSE.equals(engine.hasRight(AppRight.CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE))) //verify if edischarge is clinically complete,this is set in  edischargepreviewcomponenet tab   //wdev-13658
			{
				form.txtActions().setEnabled(false);
				form.txtGeneral().setEnabled(false);
				form.txtNurseEnabled().setEnabled(false);
				form.grdFollowUp().setEnabled(false);
				form.getContextMenus().getGenericGridAddItem().setVisible(false);
				form.getContextMenus().getGenericGridUpdateItem().setVisible(false);
			}
		}
		//verify if discharge record is clinically complete
		if(form.getLocalContext().getcurentSurgicalOpNotes() != null)
			if(form.getLocalContext().getcurentSurgicalOpNotes().getSignOffHCP() != null && form.getLocalContext().getcurentSurgicalOpNotes().getSignOffDateTime() != null && Boolean.FALSE.equals(engine.hasRight(AppRight.CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE)))  //wdev-13658
			{
				form.txtActions().setEnabled(false);
				form.txtGeneral().setEnabled(false);
				form.txtNurseEnabled().setEnabled(false);
				form.grdFollowUp().setEnabled(false);
				form.getContextMenus().getGenericGridAddItem().setVisible(false);
				form.getContextMenus().getGenericGridUpdateItem().setVisible(false);
			}
		form.getLocalContext().setcomponentValueChanged(new Integer(1));
		form.fireCustomControlValueChanged();
	}

	
	protected void onBtnCancelClick() throws PresentationLogicException 
	{
		form.setMode(FormMode.VIEW);
		clearInstanceControls();
		updateControlsState();
		updateContextMenus();
		open();
	}

	
	protected void onBtnSaveClick() throws PresentationLogicException 
	{
		if(save())
		{
			open();
			//if fields from "Sign Off" (i.e Completing Clinical and Date of Completion ) are populated and save are made that means clinically complete
			if(form.getLocalContext().getcurentSurgicalOpNotes().getSignOffDateTimeIsNotNull() && form.getLocalContext().getcurentSurgicalOpNotes().getSignOffHCPIsNotNull() )
			{
				form.getLocalContext().setcomponentValueChanged(new Integer(2));
				form.fireCustomControlValueChanged();
			}
		}
		
	}

	
	protected void onGrdFollowUpGridComboBoxSelectionChanged(int column,grdFollowUpRow row, Object value) throws PresentationLogicException 
	{
		verifyAndDisableNonHospFollowUp(row);
		
	}

	private void verifyAndDisableNonHospFollowUp(grdFollowUpRow row)
	{
		if(row.getColHospFollowUp()!=null && row.getColHospFollowUp().equals(YesNo.NO)){
			row.setColINReadOnly(true);
			row.setColPeriodReadOnly(true);
			row.setColHCPReadOnly(true);
			row.setColSpecReadOnly(true);
			row.setColLocReadOnly(true);
			
			row.setColIN(null);
			row.setColPeriod(null);
			row.getColHCP().setValue(null);
			row.setColSpec(null);
			row.getColLoc().setValue(null);
		}
		else{
			row.setColINReadOnly(false);
			row.setColPeriodReadOnly(false);
			row.setColHCPReadOnly(false);
			row.setColSpecReadOnly(false);
			row.setColLocReadOnly(false);
		}
	}

	
	protected void onGrdFollowUpGridQueryComboBoxTextSubmited(int column,grdFollowUpRow row, String text) throws PresentationLogicException 
	{
		if (column == 4) // HCP
			loadHcps(row, text);
		else if (column == 6)// Location
			loadLocations(row, text);
		
	}
	private void loadLocations(grdFollowUpRow row, String text)
	{
		LocationLiteVoCollection voColl = domain.listWards(text);
		row.getColLoc().clear();
		for (int i = 0; voColl != null && i < voColl.size(); i++)
			row.getColLoc().newRow(voColl.get(i), voColl.get(i).getName().toString());

		if (voColl != null && voColl.size() == 1)
			row.getColLoc().setValue(voColl.get(0));
		else if (voColl != null && voColl.size() > 1)
			row.getColLoc().showOpened();

	}

	private void loadHcps(grdFollowUpRow row, String text)
	{
		HcpFilter voFilter = new HcpFilter();
		PersonName voName = new PersonName();
		voName.setSurname(text);
		voFilter.setQueryName(voName);
		voFilter.setHcpType(HcpDisType.MEDICAL);
		HcpLiteVoCollection voColl = domain.listHCPs(voFilter);
		row.getColHCP().clear();
		for (int i = 0; voColl != null && i < voColl.size(); i++)
			row.getColHCP().newRow(voColl.get(i), voColl.get(i).getName().toString());

		if (voColl != null && voColl.size() == 1)
			row.getColHCP().setValue(voColl.get(0));
		else if (voColl != null && voColl.size() > 1)
			row.getColHCP().showOpened();
	}
	
	protected void updateContextMenus()
	{
		form.getContextMenus().hideAllGenericGridMenuItems();
		form.getContextMenus().getGenericGridAddItem().setVisible(form.getMode().equals(FormMode.EDIT));
		form.getContextMenus().getGenericGridUpdateItem().setVisible(false/*form.getMode().equals(FormMode.EDIT) && form.grdFollowUp().getSelectedRow() != null)*/);
		if(form.getLocalContext().getcurentSurgicalOpNotes() != null)
			if(form.getLocalContext().getcurentSurgicalOpNotes().getSignOffHCP() != null && form.getLocalContext().getcurentSurgicalOpNotes().getSignOffDateTime() != null && Boolean.FALSE.equals(engine.hasRight(AppRight.CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE)))  //wdev-14074
			{
				
				form.getContextMenus().getGenericGridAddItem().setVisible(false);
				form.getContextMenus().getGenericGridUpdateItem().setVisible(false);
			}
	}
	
	protected void onGrdFollowUpSelectionChanged()
			throws PresentationLogicException 
	{
		
		
	}
	//This method build the report save the report in a file.pdf and upload the file.pdf
	//return the name of the file
	private String buildReport(ArrayList<String> errors)
	{		
	
		CareContextShortVo currentCareContext = form.getGlobalContext().Core.getCurrentCareContext();
		Object[] obj = null;
		QueryBuilderClient client = new QueryBuilderClient(urlQueryServer, engine.getSessionId());
		
		Integer reportID = 217;
		obj = domain.getSystemReportAndTemplate(reportID);
		client.addSeed(new SeedValue("CareContext_id",  currentCareContext.getID_CareContext(), Integer.class));
		
		if(obj == null || obj.length < 2)
		{
			errors.add("I could not get the report and template !");
			return null;
		}
		
		if(obj[0] == null || obj[1] == null)
		{
			errors.add("The report has not been deployed !");
			return null;
		}
		byte[] urlreport = null;		
		try
		{
			urlreport = client.buildReport((String)obj[0], (String)obj[1], urlReportServer, "PDF", "", 1);
			
		}
		catch (QueryBuilderClientException e1)
		{
			errors.add("Error creating report. Verify that Report builder is running");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");		
		Date now = new Date();
		String urlPdfUploadServer = ConfigFlag.GEN.PDF_UPLOAD_URL.getValue();
		if (urlPdfUploadServer == "")
		{
			catchErrors("PDF_UPLOAD_URL flag need to be set",SystemLogLevel.ERROR);
			errors.add("PDF_UPLOAD_URL flag need to be set");
			return null;
		}
		String fileName = "surgopnotes" + "_" + engine.getSessionId() + "_" + df.format(now) + ".pdf";
		
		try 
		{
			engine.uploadFile(ConfigFlag.GEN.PDF_UPLOAD_URL.getValue(), urlreport, fileName,  ConfigFlag.GEN.FILE_UPLOAD_DIR.getValue());
		}
		catch (Exception e)
		{
			catchErrors(e.toString(), SystemLogLevel.ERROR);
			errors.add("Error uploading report: " + e.getMessage());
		
			return null; 
		} 
		return fileName;
	}
	private String[] getErrorsFromList(ArrayList<String> listOfErrors)
	{
		if (listOfErrors == null || listOfErrors.size() == 0)
			return null;
		String[] errors = new String[listOfErrors.size()];
		listOfErrors.toArray(errors);
		return errors;
	}

	private void catchErrors(String error, SystemLogLevel level) 
	{
		if (level.equals(SystemLogLevel.ERROR)) {
			engine.showMessage(error);
		}
		
		LOG.error(error);
		engine.createSystemLogEntry(SystemLogType.FILE_UPLOADING, level, error);
	}
	private void initialise()
	{
		form.ccCompletingClinician().initialize(MosType.MEDIC);
		form.getLocalContext().setcurentSurgicalOpNotes(null);
		form.getLocalContext().setselectedRecord(null);
		form.btnGenerate().setEnabled(true);
		form.ccCompletingClinician().isRequired(true);	//wdev-13658
		//wdev-13658
		form.chkSurgicalOperationNotesComplete().setValue(Boolean.FALSE);
		form.ccCompletingClinician().setVisible(false);
		form.dtimCompletion().setVisible(false);
		form.lblCompletingClinician().setVisible(false);
		form.lblDateofCompletion().setVisible(false);
		//-------------------
		
	}

	public void setReadOnly(Boolean isRead) 
	{
		if( Boolean.TRUE.equals(engine.hasRight(AppRight.CAN_UNDO_SURGICAL_OPERATION_NOTES_COMPLETE)))		//wdev-13658
			form.getLocalContext().setReadOnly(false);														//wdev-13658
		else
			form.getLocalContext().setReadOnly(isRead);
		updateControlsState();		
	}
	public void refresh() 
	{
		try 
		{
			open();
		} 
		catch (PresentationLogicException e) 
		{
			
			//e.printStackTrace();
			engine.showMessage("Error open the form: " + e.getMessage());
			return;
		} 
		form.setMode(FormMode.VIEW);
		updateControlsState();
		updateContextMenus();
		
		//if(form.getLocalContext().getReadOnly() != null && form.getLocalContext().getReadOnly() == true)
		//	form.btnEdit().setEnabled(false);
	}
    
	//this method must be called after a fireCustomControlValueChanged()
	//return 1 - Edit button was pressed
	//       2 - Save button was pressed - copmlete
	public Integer componentValueChanged() 
	{
		return form.getLocalContext().getcomponentValueChanged();
	}

	@Override
	protected void onBtnGenerateClick() throws PresentationLogicException
	{
		form.getLocalContext().setSurgicalOperationSummary(domain.getSurgicalOperationSUmmary(form.getGlobalContext().Core.getCurrentCareContext())); //wdev-15917
		if (saveGeneratedDocument())
			open();
		
	}

	//wdev-13658
	protected void onChkSurgicalOperationNotesCompleteValueChanged()throws PresentationLogicException 
	{
		
		setHideVisbleSIgnOffControls(Boolean.TRUE);
	}
	//wdev-13658
	protected void setHideVisbleSIgnOffControls(Boolean defaultClin)
	{
		if(Boolean.TRUE.equals(form.chkSurgicalOperationNotesComplete().getValue()))
		{
			form.ccCompletingClinician().setVisible(true);
			form.dtimCompletion().setVisible(true);
			form.lblCompletingClinician().setVisible(true);
			form.lblDateofCompletion().setVisible(true);
			if(Boolean.TRUE.equals(defaultClin))
			{
				if(form.ccCompletingClinician().getValue() == null)
				{
					if(domain.getHcpLiteUser() instanceof HcpLiteVo)
					{
						HcpLiteVo temp = (HcpLiteVo)domain.getHcpLiteUser();
						if(temp != null)
						{
							HcpDisType hcptype =getParentNodeHcp(temp.getHcpType()); 
							if( hcptype != null && hcptype.equals(HcpDisType.MEDICAL))
								form.ccCompletingClinician().setValue(temp);
						}
					}
				}
				form.dtimCompletion().setValue(new DateTime());
			}
		}
		else
		{
			form.ccCompletingClinician().setVisible(false);
			form.dtimCompletion().setVisible(false);
			form.lblCompletingClinician().setVisible(false);
			form.lblDateofCompletion().setVisible(false);
		}
	}
	//wdev-13658
	private HcpDisType getParentNodeHcp(HcpDisType hcpvo)
	{
		if(hcpvo == null)
			return null;
		if(hcpvo.getParent() == null)
			return hcpvo;
		else
			return getParentNodeHcp(hcpvo.getParent());
		
	}
}