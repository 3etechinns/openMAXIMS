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
// This code was generated by Florin Blindu using IMS Development Environment (version 1.80 build 4342.23748)
// Copyright (C) 1995-2012 IMS MAXIMS. All rights reserved.

package ims.emergency.forms.triagepatientmeds;

import java.util.ArrayList;

import ims.configuration.gen.ConfigFlag;
import ims.core.admin.vo.CareContextRefVo;
import ims.core.admin.vo.EpisodeOfCareRefVo;
import ims.core.patient.vo.PatientRefVo;
import ims.core.vo.AuthoringInformationVo;
import ims.core.vo.HcpLiteVo;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.emergency.vo.EmergencyAttendanceForPatientMedsVo;
import ims.emergency.vo.HistoryOfPatientMedsVo;
import ims.emergency.vo.PatientMedsVo;
import ims.emergency.vo.PatientMedsVoCollection;
import ims.emergency.vo.PatientProblemForPatientMedsVo;
import ims.emergency.vo.TrackingForClinicianWorklistAndTriageVo;
import ims.emergency.vo.TriageForPatientMedsVo;
import ims.emergency.vo.enums.EdAssessment_CustomControlsEvents;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.DateTime;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		//WDEV-17337
		form.getLocalContext().setSelectedEvent(EdAssessment_CustomControlsEvents.CANCEL);
		form.fireCustomControlValueChanged();
		
		open();
	}

	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		//WDEV-17337
		form.getLocalContext().setSelectedEvent(EdAssessment_CustomControlsEvents.SAVE);
		form.fireCustomControlValueChanged();
		
		if (save())
		{
			open();
		}
	}

	@Override
	protected void onBtnEditClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (domain.getHcpUser() == null)
		{
			engine.showMessage("Current User is not a HCP user");
			return;

		}

		updateInstance();
	}

	@Override
	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if (domain.getHcpUser() == null)
		{
			engine.showMessage("Current User is not a HCP user");
			return;

		}
		newInstance();
	}

	@Override
	protected void onRecbrPatientMedValueChanged() throws ims.framework.exceptions.PresentationLogicException
	{
		getSelectedInstance();
		updateControlsState();
	}

	public void open() throws ims.framework.exceptions.PresentationLogicException
	{
		populateScreenFromData();
		form.setMode(FormMode.VIEW);
	}

	private void populateScreenFromData()
	{
		populateRecordBrowser();
		getSelectedInstance();
		populateHistoryOfPatientMeds();	//wdev-15932
		
		
		//----------------
		
	}
	//wdev-15932
	private void populateHistoryOfPatientMeds()
	{
		form.richMedicationHistory().setValue(null);
		if( form.getLocalContext().getTrackingForClinicianWorklistAndTriageIsNotNull() && form.getLocalContext().getTrackingForClinicianWorklistAndTriage().getEpisodeIsNotNull() && Boolean.TRUE.equals(form.getLocalContext().getTrackingForClinicianWorklistAndTriage().getEpisode().getMedPatientConsent()))  //wdev-17819
		{
			String NhsNumber = domain.getNHSNumber(form.getLocalContext().getPatientRef());
			form.getLocalContext().setNhsNumber(NhsNumber);
			if( NhsNumber != null)
			{
				//HistoryOfPatientMedsVoCollection tempColl = domain.getHistoryOfPatientMeds(NhsNumber);
				HistoryOfPatientMedsVo tempVo = domain.getHistoryOfPatientMedsVo(NhsNumber);
				if( form.getLocalContext().getselectedPatientMeds() == null && tempVo != null) 	//wdev-17858
				{
					if( !Boolean.TRUE.equals(form.getLocalContext().getTrackingForClinicianWorklistAndTriage().getIsDischarged())) //wdev-17858
						form.richMedicationHistory().setValue(tempVo.getExternalMedDetails());
					
				}
				else if( form.getLocalContext().getselectedPatientMedsIsNotNull() )     		//wdev-17858
				{
					form.richMedicationHistory().setValue(form.getLocalContext().getselectedPatientMeds().getMedicationDetailsFromGP());
				}
					
				
			}
		}
	}

	private void getSelectedInstance()
	{
		if (form.recbrPatientMed().getValue() == null)
			return;

		form.getLocalContext().setselectedCareContext(form.recbrPatientMed().getValue());
		PatientMedsVo patientMeds = domain.getPatientMedsForCareContext(form.getLocalContext().getselectedCareContext());
		form.getLocalContext().setselectedPatientMeds(patientMeds);

		populateInstanceControls(patientMeds);

	}

	private void populateInstanceControls(PatientMedsVo patientMeds)
	{
		clearInstanceControls();

		if (patientMeds == null)
		{
			return;
		}

		if (patientMeds.getCurrentMedicationDetailsIsNotNull())
		{
			//form.richTextPatMedDescription().setValue(patientMeds.getCurrentMedicationDetails());
			form.txtPatMedDescription().setValue(patientMeds.getCurrentMedicationDetails());	//wdev-15932
		}
	}

	private void populateRecordBrowser()//WDEV-17602
	{
		form.recbrPatientMed().clear();
		
		PatientMedsVoCollection collTriagePatientMeds = domain.getAllPatientMeds(form.getLocalContext().getPatientRef());

		if (collTriagePatientMeds == null)
			return;

		for (int i = 0; i < collTriagePatientMeds.size(); i++)
		{
			PatientMedsVo patientMeds = collTriagePatientMeds.get(i);

			if (patientMeds == null)
				continue;

			StringBuffer recordBrowserText = new StringBuffer();

			TriageForPatientMedsVo triage = domain.getTriageByCareContext(patientMeds.getAttendance());
			PatientProblemForPatientMedsVo problem = (triage!=null ? triage.getMainPresentingProblem() : null);

			recordBrowserText.append(getTextToDisplayFromEmergencyAttendance(patientMeds.getAttendance()));

			if (problem != null)
			{
				if (problem.getPatientProblemIsNotNull())
				{
					recordBrowserText.append(" - "+problem.getPatientProblem());
				}
			}

			if (form.getLocalContext().getCareContextRef().equals(patientMeds.getAttendance()))
			{
				form.recbrPatientMed().newRow(patientMeds.getAttendance(), recordBrowserText.toString(), Color.Green);
				continue;
			}
			form.recbrPatientMed().newRow(patientMeds.getAttendance(), recordBrowserText.toString());
		}

		ArrayList collRecBrowser = form.recbrPatientMed().getValues();
		
		if (!collRecBrowser.contains(form.getLocalContext().getCareContextRef()))
		{
			form.recbrPatientMed().newRow(form.getLocalContext().getCareContextRef(), getTextToDisplayFromEmergencyAttendance(form.getLocalContext().getCareContextRef()).toString(), Color.Green);
		}
		form.recbrPatientMed().setValue(form.getLocalContext().getCareContextRef());

	}

	//WDEV-17602
	private StringBuffer getTextToDisplayFromEmergencyAttendance(CareContextRefVo careContextRefVo)
	{
		StringBuffer emergAttText = new StringBuffer();
		EmergencyAttendanceForPatientMedsVo emergAttendance = domain.getEmergencyAttendanceByCareContext(careContextRefVo);
		
		if (emergAttendance != null)
		{
			if (emergAttendance.getArrivalDateTimeIsNotNull())
			{
				emergAttText.append(emergAttendance.getArrivalDateTime());
			}

			if (emergAttendance.getDischargeDateTimeIsNotNull())
			{
				emergAttText.append(" - "+emergAttendance.getDischargeDateTime());
			}
		}
		
		return emergAttText;
	}

	public void clearInstanceControls()
	{
		//form.richTextPatMedDescription().setValue(null);
		form.txtPatMedDescription().setValue(null);					//wdev-15932
	}

	public void newInstance() throws ims.framework.exceptions.PresentationLogicException
	{
		clearInstanceControls();
		form.getLocalContext().setselectedPatientMeds(null);
		form.setMode(FormMode.EDIT);
	}

	public boolean save() throws ims.framework.exceptions.PresentationLogicException
	{
		PatientMedsVo patientMedsToSave;
		patientMedsToSave = populateInstanceDataFromScreen(form.getLocalContext().getselectedPatientMeds());

		String[] errors = patientMedsToSave.validate();
		if (errors != null && errors.length > 0)
		{
			engine.showErrors(errors);
			return false;
		}

		PatientMedsVo patientMedsExistent = domain.getPatientMedsForCareContext(patientMedsToSave.getAttendance());
		// Condition to create only one PatientMeds
		if (!patientMedsToSave.getID_PatientMedicationOnAttendanceIsNotNull() && patientMedsExistent != null)
		{
			engine.showMessage("A PatientMeds already exist. The screen will be refreshed");
			form.getLocalContext().setselectedCareContext(null);
			open();
			return false;
		}

		// Check SOE
		if (patientMedsToSave.getID_PatientMedicationOnAttendanceIsNotNull() && domain.isStale(patientMedsToSave))
		{
			engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			form.getLocalContext().setselectedCareContext(null);
			open();
			return false;
		}

		try
		{
			PatientMedsVo patientMedsSaved = domain.save(patientMedsToSave);
			form.getLocalContext().setselectedCareContext(patientMedsSaved.getAttendance());
		}
		catch (StaleObjectException e)
		{
			engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return false;
		}
		catch (UniqueKeyViolationException e)
		{
			engine.showMessage("A PatientMeds for the selected Attendance already exist. The screen will be refreshed");
			open();
			return false;
		}

		return true;
	}

	private PatientMedsVo populateInstanceDataFromScreen(PatientMedsVo patientMeds)
	{

		if (patientMeds == null)
		{
			patientMeds = new PatientMedsVo();
		}

		patientMeds.setPatient(form.getLocalContext().getPatientRef());
		patientMeds.setAttendance(form.getLocalContext().getCareContextRef());
		patientMeds.setEpisode(form.getLocalContext().getEpisodeOfCareRef());

		//patientMeds.setCurrentMedicationDetails(form.richTextPatMedDescription().getValue());
		patientMeds.setCurrentMedicationDetails(form.txtPatMedDescription().getValue());		//wdev-15932
		
		//wdev-15932
		if(form.getLocalContext().getNhsNumberIsNotNull())
		{
			HistoryOfPatientMedsVo tempVo = domain.getHistoryOfPatientMedsVo(form.getLocalContext().getNhsNumber());
			if( tempVo != null && !Boolean.TRUE.equals(form.getLocalContext().getTrackingForClinicianWorklistAndTriage().getIsDischarged()))    //wdev-17858
				patientMeds.setMedicationDetailsFromGP(tempVo.getExternalMedDetails());		
		}
		//---------

		AuthoringInformationVo authoringInfo = new AuthoringInformationVo();

		authoringInfo.setAuthoringDateTime(new DateTime());

		Object hcp = domain.getHcpUser();
		if (hcp instanceof HcpLiteVo)
		{
			authoringInfo.setAuthoringHcp((HcpLiteVo) domain.getHcpUser());
		}

		patientMeds.setAuthoringInformation(authoringInfo);
		return patientMeds;

	}

	public void updateControlsState()
	{
		boolean isCurrentCareContext = false;

		if (form.recbrPatientMed().getValue() != null)
		{

			isCurrentCareContext = form.getLocalContext().getCareContextRef().equals(form.recbrPatientMed().getValue());
		}
		else //WDEV-17602
		{
			isCurrentCareContext=true;
		}

		form.btnNew().setVisible(FormMode.VIEW.equals(form.getMode()) && isCurrentCareContext && ((form.recbrPatientMed().getValue() != null && domain.getPatientMedsForCareContext(form.recbrPatientMed().getValue()) == null ) || form.recbrPatientMed().getValue() == null)); //WDEV-17602
		form.btnNew().setEnabled(FormMode.VIEW.equals(form.getMode()) && ((form.recbrPatientMed().getValue() != null && domain.getPatientMedsForCareContext(form.recbrPatientMed().getValue()) == null)|| form.recbrPatientMed().getValue() == null ) && isCurrentCareContext && Boolean.TRUE.equals(form.getLocalContext().getisEnabled())); //WDEV-17602

		form.btnEdit().setVisible(FormMode.VIEW.equals(form.getMode()) && !form.btnNew().isVisible() && form.recbrPatientMed().getValue() != null && isCurrentCareContext);
		form.btnEdit().setEnabled(FormMode.VIEW.equals(form.getMode()) && !form.btnNew().isVisible() && form.recbrPatientMed().getValue() != null && isCurrentCareContext && isCurrentCareContext && Boolean.TRUE.equals(form.getLocalContext().getisEnabled()));
	}

	public void updateInstance()
	{
		form.setMode(FormMode.EDIT);
	}

	@Override
	protected void onFormModeChanged()
	{
		updateControlsState();
	}

	@Override
	protected void onFormOpen(Object[] args) throws PresentationLogicException
	{
		// TODO Auto-generated method stub

	}

	public void initialize(PatientRefVo patientRef, CareContextRefVo careContextRefVo, EpisodeOfCareRefVo episodeOfCareRef, TrackingForClinicianWorklistAndTriageVo tracking)  //wdev-17819
	{
		form.getLocalContext().setPatientRef(patientRef);
		form.getLocalContext().setEpisodeOfCareRef(episodeOfCareRef);
		form.getLocalContext().setCareContextRef(careContextRefVo);
		form.getLocalContext().setTrackingForClinicianWorklistAndTriage(tracking);	//wdev-17819

		form.getLocalContext().setselectedCareContext(careContextRefVo);
		try
		{
			open();
		}
		catch (PresentationLogicException e)
		{
			e.printStackTrace();
		}
	}

	public void setEnabled(Boolean argument)
	{
		form.getLocalContext().setisEnabled(argument);
		updateControlsState();
	}
	
	//WDEV-17337
	public EdAssessment_CustomControlsEvents getSelectedEvent()
	{
		return form.getLocalContext().getSelectedEvent();
	}
	
	//WDEV-17337
	public void resetSelectedEvent()
	{
		form.getLocalContext().setSelectedEvent(null);
	}

	
}