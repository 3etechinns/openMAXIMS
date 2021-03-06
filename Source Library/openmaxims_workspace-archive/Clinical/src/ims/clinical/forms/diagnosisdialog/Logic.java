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
// This code was generated by Ander Telleria using IMS Development Environment (version 1.65 build 3187.17423)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.clinical.forms.diagnosisdialog;

import ims.clinical.vo.PatientDiagnosisStatusVo;
import ims.clinical.vo.PatientDiagnosisStatusVoCollection;
import ims.configuration.AppRight;
import ims.core.vo.AuthoringInformationVo;
import ims.core.vo.CSPrimaryDiagnosisShortVo;
import ims.core.vo.CSPrimaryDiagnosisShortVoCollection;
import ims.core.vo.DiagLiteVo;
import ims.core.vo.DiagLiteVoCollection;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.PatientDiagnosisAtConsultationVo;
import ims.core.vo.PatientDiagnosisAtConsultationVoCollection;
import ims.core.vo.lookups.SourceofInformation;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.DateTime;
import ims.framework.utils.PartialDate;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialize(args);
	}
	
	private void initialize(Object[] args)
	{
		if(args != null && args.length > 0)
		{
			if(args[0] instanceof Boolean)
			{
				form.getLocalContext().setAddedDuringCoding(Boolean.TRUE);
			}
		}
		
		initializeCustomControl();
		
		if (form.getGlobalContext().Core.getPatientDiagnosisAtConsultation()!=null)
		{
			populateScreenFromData( form.getGlobalContext().Core.getPatientDiagnosisAtConsultation());
		}
		else
		{
			AuthoringInformationVo voAuthoringInformation=new AuthoringInformationVo();
			voAuthoringInformation.setAuthoringDateTime(new DateTime());
			if (domain.getHcpLiteUser()!=null)
				voAuthoringInformation.setAuthoringHcp((HcpLiteVo) domain.getHcpLiteUser());
			form.ccAuthoringInfo().setValue(voAuthoringInformation);
		}

		if (form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList()==null)
		{
			PatientDiagnosisAtConsultationVoCollection diagnosisCollection=new PatientDiagnosisAtConsultationVoCollection();
			form.getGlobalContext().Core.setPatientDiagnosisAtConsultationList(diagnosisCollection);
		}

		if (domain.getHcpLiteUser() == null)
		{
			form.chkMain().setEnabled(false);
			form.cmbStatus().setEnabled(false);
			form.qmbDiagnosis().setEnabled(false);
			form.setccAuthoringInfoEnabled(false);

			if (engine.hasRight(AppRight.ALLOW_EDIT_CONSULTATION_DIAGNOSIS))
			{
				form.qmbDiagnosis().setEnabled(false);
			}
		}
		
		if (form.getGlobalContext().Clinical.getDisableMainCheckboxIsNotNull() && form.getGlobalContext().Clinical.getDisableMainCheckbox())
		{
			form.chkMain().setEnabled(false);
		}
	}
	
	private void populateScreenFromData(PatientDiagnosisAtConsultationVo patDiagnosis)
	{
		if (patDiagnosis.getAuthoringInfoIsNotNull())
			form.ccAuthoringInfo().setValue(patDiagnosis.getAuthoringInfo());

		form.getLocalContext().setDiagnosisDescription(patDiagnosis.getDiagnosisDescription());
		
		if(patDiagnosis.getDiagnosis() != null)
		{
			form.qmbDiagnosis().newRow(patDiagnosis.getDiagnosis(), patDiagnosis.getDiagnosisDescription());
			form.qmbDiagnosis().setValue(patDiagnosis.getDiagnosis());
		}
		else
		{
			DiagLiteVo d = new DiagLiteVo();
			d.setDiagnosisName(patDiagnosis.getDiagnosisDescription());
			form.qmbDiagnosis().newRow(d, d.getDiagnosisName());
			form.qmbDiagnosis().setValue(d);
		}

		form.qmbDiagnosis().setEnabled(true);
		
		CSPrimaryDiagnosisShortVoCollection voColl = patDiagnosis.getPrimaryForCareSpells();
		form.chkMain().setValue(false);
		for (int i = 0 ; voColl != null && i < voColl.size() ; i++)
		{
			if (voColl.get(i).getCareContextIsNotNull() 
					&& voColl.get(i).getCareContext().equals(form.getGlobalContext().Core.getCurrentCareContext()) && Boolean.TRUE.equals(voColl.get(i).getIsActive()))//WDEV-17356
				form.chkMain().setValue(true);
		}

		if (patDiagnosis.getCurrentStatusIsNotNull())
			if (patDiagnosis.getCurrentStatus().getStatusIsNotNull())
				form.cmbStatus().setValue(patDiagnosis.getCurrentStatus().getStatus());

		form.ccAuthoringInfo().setMode(FormMode.VIEW);
	}

	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		PatientDiagnosisAtConsultationVo diagnosisVo = populateDataFromScreen();
		String[] errors = diagnosisVo.validate();
		boolean equals=false;
		
		if(form.getGlobalContext().Core.getCurrentCareContext() != null)
		{
			form.getGlobalContext().Core.setPatientDiagnosisAtConsultationList(domain.listPatientDiagnosis(form.getGlobalContext().Core.getCurrentCareContext()));
		}

		if (errors == null)
		{
			for (int i=0;i<form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList().size();i++)
			{
				PatientDiagnosisAtConsultationVo patDiagnosis = form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList().get(i);
				if (diagnosisVo != null
						&& patDiagnosis != null)
				{
					//If they are the same PatientDiagnosis
					if (diagnosisVo.getID_PatientDiagnosisIsNotNull()&&patDiagnosis.getID_PatientDiagnosisIsNotNull()&&diagnosisVo.getID_PatientDiagnosis().equals(patDiagnosis.getID_PatientDiagnosis())
							||
							(
								diagnosisVo.getDiagnosisIsNotNull() && patDiagnosis.getDiagnosisIsNotNull()
								&& (diagnosisVo.getDiagnosis().getID_DiagnosisIsNotNull()&& patDiagnosis.getDiagnosis().getID_DiagnosisIsNotNull())
								&& (diagnosisVo.getDiagnosis().getID_Diagnosis().equals(patDiagnosis.getDiagnosis().getID_Diagnosis())||(patDiagnosis.getDiagnosis().getID_Diagnosis()==-1&&diagnosisVo.getDiagnosis().getID_DiagnosisIsNotNull()))
								&& (diagnosisVo.getAuthoringInfoIsNotNull()&& patDiagnosis.getAuthoringInfoIsNotNull())
								&& (diagnosisVo.getAuthoringInfo().getAuthoringDateTimeIsNotNull()&&patDiagnosis.getAuthoringInfo().getAuthoringDateTimeIsNotNull()&&diagnosisVo.getAuthoringInfo().getAuthoringDateTime().equals(patDiagnosis.getAuthoringInfo().getAuthoringDateTime()))
								&& (diagnosisVo.getAuthoringInfo().getAuthoringHcpIsNotNull()&&patDiagnosis.getAuthoringInfo().getAuthoringHcpIsNotNull()&&diagnosisVo.getAuthoringInfo().getAuthoringHcp().equals(patDiagnosis.getAuthoringInfo().getAuthoringHcp()))))

					{			
						PatientDiagnosisAtConsultationVoCollection diagnosises= form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList();
						diagnosises.remove(i);
						diagnosises.add(i,diagnosisVo);
						equals=true;

						form.getGlobalContext().Core.setPatientDiagnosisAtConsultationList(diagnosises);
					}
				}
				if (!equals)
				{
					PatientDiagnosisAtConsultationVoCollection listDiag = form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList();//.add(diagnosisVo);
					listDiag.add(diagnosisVo);
					form.getGlobalContext().Core.setPatientDiagnosisAtConsultationList(listDiag);
				}
			}
			if (form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList().size()==0)
			{
				PatientDiagnosisAtConsultationVoCollection listDiag = new PatientDiagnosisAtConsultationVoCollection();
				listDiag.add(diagnosisVo);
				form.getGlobalContext().Core.setPatientDiagnosisAtConsultationList(listDiag);
			}
			
			clearPreviousPrimaryDiagnosis(diagnosisVo);
			
			engine.close(DialogResult.OK);
		}
		else
			engine.showErrors(errors);
	}
	
	private void clearPreviousPrimaryDiagnosis(PatientDiagnosisAtConsultationVo diagnosisVo) 
	{
		if(diagnosisVo == null)
			return;
		
		if(form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList() == null || form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList().size() == 0)
			return;
		
		if(!form.chkMain().getValue())
			return;
		
		for(PatientDiagnosisAtConsultationVo diag : form.getGlobalContext().Core.getPatientDiagnosisAtConsultationList())	
		{
			if(diag == null || diag.equals(diagnosisVo))
				continue;
			
			CSPrimaryDiagnosisShortVoCollection primaryColl = diag.getPrimaryForCareSpells();
			
			if(primaryColl == null || primaryColl.size() == 0)
				continue;
				
			for(CSPrimaryDiagnosisShortVo primary : primaryColl)
			{
				if(primary == null)
					continue;
				
				if(primary.getCareContextIsNotNull() && primary.getCareContext().equals(form.getGlobalContext().Core.getCurrentCareContext()))
				{
					primaryColl.remove(primary);
					diag.setPrimaryForCareSpells(primaryColl);
					break;
				}
			}
		}
	}

	private PatientDiagnosisAtConsultationVo populateDataFromScreen()
	{
		PatientDiagnosisAtConsultationVo record;
		if (form.getGlobalContext().Core.getPatientDiagnosisAtConsultation() == null)
		{
			record = new PatientDiagnosisAtConsultationVo();
			
			if(Boolean.TRUE.equals(form.getLocalContext().getAddedDuringCoding()))
			{
				record.setAddedDuringCoding(true);
			}
		}
		else
		{
			record = (PatientDiagnosisAtConsultationVo) form.getGlobalContext().Core.getPatientDiagnosisAtConsultation().clone();
		}
			
		record.setDiagnosis(form.qmbDiagnosis().getValue());
		record.setDiagnosisDescription(form.getLocalContext().getDiagnosisDescription());
		
		if (!record.getID_PatientDiagnosisIsNotNull())
		{
			record.setEpisodeOfCare(form.getGlobalContext().Core.getEpisodeofCareShort());
			record.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
			record.setAuthoringInfo(form.ccAuthoringInfo().getValue());
			record.setDiagnosedDate(new PartialDate());
			record.setSourceofInformation(SourceofInformation.CLINICALCONTACT);
			
			if ((form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull()) && (form.getGlobalContext().Core.getCurrentClinicalContact().getSpecialtyIsNotNull()))
			{
				record.setSpecialty(form.getGlobalContext().Core.getCurrentClinicalContact().getSpecialty());
			}
			else if ((form.getGlobalContext().Core.getEpisodeofCareShortIsNotNull()) && (form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialtyIsNotNull()))
			{
				record.setSpecialty(form.getGlobalContext().Core.getEpisodeofCareShort().getSpecialty());
			}
		}
			
		/*PatientDiagnosisStatusVo status = record.getCurrentStatus();
		if(status == null)
		{
			status = new PatientDiagnosisStatusVo();
			status.setAuthoringHCP(record.getAuthoringInfoIsNotNull()?record.getAuthoringInfo().getAuthoringHcp():null);
			status.setAuthoringDateTime(record.getAuthoringInfoIsNotNull()?record.getAuthoringInfo().getAuthoringDateTime():null);
		}
		status.setStatus(form.cmbStatus().getValue());
		
		record.setCurrentStatus(status);*/
		//wdev-17358
		if( form.cmbStatus().getValue() != null )
		{
			PatientDiagnosisStatusVoCollection voColl = record.getStatusHistory();
			if (voColl == null)
				voColl = new PatientDiagnosisStatusVoCollection();
			
			PatientDiagnosisStatusVo status = new PatientDiagnosisStatusVo();
			status.setAuthoringHCP(record.getAuthoringInfoIsNotNull()?record.getAuthoringInfo().getAuthoringHcp():null);
			status.setAuthoringDateTime(record.getAuthoringInfoIsNotNull()?record.getAuthoringInfo().getAuthoringDateTime():null);
			status.setStatus(form.cmbStatus().getValue());
			
			if (record.getCurrentStatus() == null)
			{
				
				voColl.add(status);
				record.setStatusHistory(voColl);
				record.setCurrentStatus(status);
			}
			else
			{
				if (record.getCurrentStatus().getStatusIsNotNull() && record.getCurrentStatus().getStatus().getId() != form.cmbStatus().getValue().getId())
				{
					
					voColl.add(status);
					record.setStatusHistory(voColl);
					record.setCurrentStatus(status);
				}
			}
		}

		//---------------
		

		CSPrimaryDiagnosisShortVoCollection voColl = null;
		if (record.getPrimaryForCareSpells() == null)
			voColl = new CSPrimaryDiagnosisShortVoCollection();
		else
			voColl = record.getPrimaryForCareSpells();

		if (form.chkMain().getValue())
		{
			boolean bFound = false;
			for (int i = 0 ; i < voColl.size() ; i++)
			{
				if (voColl.get(i).getCareContextIsNotNull() 
						&& voColl.get(i).getCareContext().equals(form.getGlobalContext().Core.getCurrentCareContext()) && Boolean.TRUE.equals(voColl.get(i).getIsActive()))//WDEV-17356
					bFound = true;
			}

			if ( ! bFound)
			{
				CSPrimaryDiagnosisShortVo voCPD = new CSPrimaryDiagnosisShortVo();
				// WDEV-11543
				voCPD.setEpisodeOfCare(form.getGlobalContext().Core.getEpisodeofCareShort());
				voCPD.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
				voCPD.setDiagnosis(record);
				voCPD.setIsActive(Boolean.TRUE);
				voColl.add(voCPD);

				record.setPrimaryForCareSpells(voColl);
			}
		}
		else if ( ! form.chkMain().getValue() && voColl.size() > 0)
		{
			for (int i = 0 ; i < voColl.size() ; i++)
			{
				if (voColl.get(i).getCareContextIsNotNull() 
						&& voColl.get(i).getCareContext().equals(form.getGlobalContext().Core.getCurrentCareContext()) )
					voColl.remove(i);
			}
			record.setPrimaryForCareSpells(voColl);
		}
		
		return record;
	}
	
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);
	}
	
	private void initializeCustomControl()
	{
		form.ccAuthoringInfo().initializeComponent();
	}
	
	@Override
	protected void onQmbDiagnosisValueChanged()	throws PresentationLogicException 
	{
		form.getLocalContext().setDiagnosisDescription(form.qmbDiagnosis().getValue() != null ? form.qmbDiagnosis().getValue().getDiagnosisName() : null);
	}
	
	@Override
	protected void onQmbDiagnosisTextSubmited(String value)	throws PresentationLogicException 
	{
		form.qmbDiagnosis().clear();
		DiagLiteVoCollection listDiag = domain.listDianosis(value, null);
		
		if (listDiag == null || listDiag.size() == 0)
		{
			form.qmbDiagnosis().showOpened();
			return;
		}
			
		for (int i = 0 ; i < listDiag.size() ; i++)
		{
			form.qmbDiagnosis().newRow(listDiag.get(i),listDiag.get(i).getDiagnosisName());
		}
		
		form.qmbDiagnosis().showOpened();
	}
}
