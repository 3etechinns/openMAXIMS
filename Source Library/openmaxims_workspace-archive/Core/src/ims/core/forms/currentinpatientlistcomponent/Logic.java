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
// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.66 build 3261.19720)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.core.forms.currentinpatientlistcomponent;

import ims.configuration.gen.ConfigFlag;
import ims.core.resource.place.vo.LocationRefVo;
import ims.core.vo.CurrentInpatientListFilterVo;
import ims.core.vo.HcpFilter;
import ims.core.vo.HcpLiteVo;
import ims.core.vo.HcpLiteVoCollection;
import ims.core.vo.LocMostVo;
import ims.core.vo.LocationLiteVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.PatientAlertLiteVo;
import ims.core.vo.PatientShort;
import ims.core.vo.PersonName;
import ims.core.vo.STHKCurrentInpatientListVo;
import ims.core.vo.STHKCurrentInpatientListVoCollection;
import ims.core.vo.lookups.AlertType;
import ims.core.vo.lookups.HcpDisType;
import ims.core.vo.lookups.LocationType;
import ims.core.vo.lookups.LookupHelper;
import ims.core.vo.lookups.PatIdType;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.Control;
import ims.framework.FormName;
import ims.framework.MessageButtons;
import ims.framework.MessageIcon;
import ims.framework.cn.data.TreeNode;
import ims.framework.controls.DynamicGridCell;
import ims.framework.controls.DynamicGridColumn;
import ims.framework.controls.DynamicGridRow;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.DynamicCellType;
import ims.framework.enumerations.SortMode;
import ims.framework.enumerations.SortOrder;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.Date;
import ims.vo.LookupInstVo;

import java.util.ArrayList;
import java.util.Comparator;

public class Logic extends BaseLogic
{
	/**
	 * WDEV-13136
	 * @author George Josan
	 *	Comparator for manual sorting after estimated discharge date
	 */
	private static class EstimateDischargeComparator implements Comparator<STHKCurrentInpatientListVo>
	{
		private int direction;

		public EstimateDischargeComparator(SortOrder sortOrderEstimatedDischage)
		{
			if (SortOrder.ASCENDING.equals(sortOrderEstimatedDischage))
				direction = 1;
			else
				direction = -1;
		}

		/**
		 * Function used to compare two records after estimated discharge date
		 */
		public int compare(STHKCurrentInpatientListVo o1, STHKCurrentInpatientListVo o2)
		{
			if (o1.getEstDischargeDateIsNotNull() && o2.getEstDischargeDateIsNotNull())
			{
				return o1.getEstDischargeDate().compareTo(o2.getEstDischargeDate()) * direction;
			}
			
			if (o1.getEstDischargeDateIsNotNull() && !o2.getEstDischargeDateIsNotNull())
			{
				return direction;
			}
			
			if (o2.getEstDischargeDateIsNotNull() && !o1.getEstDischargeDateIsNotNull())
			{
				return -1 * direction;
			}
			
			return 0;
		}
	}

	/**
	 * WDEV-13136
	 * @author George Josan
	 *	Comparator for manual sorting after age
	 */
	private static class AgeComparator implements Comparator<STHKCurrentInpatientListVo>
	{
		private int direction;

		public AgeComparator(SortOrder sortOrderAge)
		{
			if (SortOrder.ASCENDING.equals(sortOrderAge))
				direction = 1;
			else
				direction = -1;
		}

		/**
		 * Function used to compare two records after age
		 */
		public int compare(STHKCurrentInpatientListVo o1, STHKCurrentInpatientListVo o2)
		{
			if (o1.getPasEventIsNotNull() && o1.getPasEvent().getPatientIsNotNull() && o1.getPasEvent().getPatient().getAgeIsNotNull()
					&& o2.getPasEventIsNotNull() && o2.getPasEvent().getPatientIsNotNull() && o2.getPasEvent().getPatient().getAgeIsNotNull())
			{
				return o1.getPasEvent().getPatient().getAge().compareTo(o2.getPasEvent().getPatient().getAge()) * direction;
			}
			
			if (o1.getPasEventIsNotNull() && o1.getPasEvent().getPatientIsNotNull() && o1.getPasEvent().getPatient().getAgeIsNotNull()
					&& (!o2.getPasEventIsNotNull() || !o2.getPasEvent().getPatientIsNotNull() || !o2.getPasEvent().getPatient().getAgeIsNotNull()) )
			{
				return direction;
			}
			
			if (o1.getPasEventIsNotNull() && o1.getPasEvent().getPatientIsNotNull() && o1.getPasEvent().getPatient().getAgeIsNotNull()
					&& (!o2.getPasEventIsNotNull() || !o2.getPasEvent().getPatientIsNotNull() || !o2.getPasEvent().getPatient().getAgeIsNotNull()) )
			{
				return -1 * direction;
			}

			return 0;
		}
	}

	/**
	 * WDEV-18011 
	 *	Comparator for manual sorting after alerts image
	 */
	private static class AlertsComparator implements Comparator<STHKCurrentInpatientListVo>
	{
		private int direction;

		public AlertsComparator(SortOrder sortOrderAlert)
		{
			if (SortOrder.ASCENDING.equals(sortOrderAlert))
				direction = 1;
			else
				direction = -1;
		}

		/**
		 * Function used to compare two records after alert image 
		 */
		public int compare(STHKCurrentInpatientListVo o1, STHKCurrentInpatientListVo o2)
		{
			if (o1.getPasEvent()!= null && o1.getPasEvent().getPatient() != null && 
				o2.getPasEvent() != null && o2.getPasEvent().getPatient() != null)
			{
				Integer val1 = Boolean.TRUE.equals(o1.getActiveAlerts()) ? 1 : 0;
				Integer val2 = Boolean.TRUE.equals(o2.getActiveAlerts()) ? 1 : 0;
				
				if (val1 != 0 && val2 != 0)
				{
					return val1.compareTo(val2) * direction;
				}

				if (val1 != 0 && val2 == 0)
				{
					return direction;
				}

				if (val2 != 0 && val1 == 0)
				{
					return -1 * direction;
				}	
			}

		return 0;
		}
	}
	
	public static final Integer		COLSURNAME			= new Integer(0);
	public static final Integer		COLFORENAME			= new Integer(1);
	public static final Integer		COLHOSPNUM			= new Integer(-1);
	public static final Integer		COLAGE				= new Integer(-2);
	public static final Integer		COLSEX				= new Integer(-3);
	public static final Integer		COLBEDTYPE			= new Integer(-4);
	public static final Integer		COLALERTS			= new Integer(-5);
	public static final Integer		COLWARD				= new Integer(-6);
	public static final Integer		COLLOCATION			= new Integer(-7);
	public static final Integer		COLCONSULANT		= new Integer(-8);
	public static final Integer		COLESTDISCH			= new Integer(-9);
	public static final Integer		COLCOMMENT			= new Integer(-10);
	public static final Integer		COLHOMELEAVE		= new Integer(-11);
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialize();
	}
	

	@Override
	/**
	 * WDEV-13136
	 * Event handler for column header clicked
	 */
	protected void onDyngrdCurrIPColumnHeaderClicked(DynamicGridColumn column)
	{
		// Sort patients after selected column
		// Start on the assertion that column identifier is an Integer
		sortPatients((Integer) column.getIdentifier());
		
		// Update controls & context menu states
		updateContextMenus();
	}

	/**
	 * WDEV-13136
	 * Function used to sort patients (after Age & Estimate Date)
	 */
	private void sortPatients(Object columnIdentifer)
	{
		// Get records from grid
		STHKCurrentInpatientListVoCollection gridValues = populateRecordsFromGrid();
		
		// Toggle sort order for column
		sortOrderToggle(columnIdentifer);
		
		// Determine column, sort records
		if (COLAGE.equals(columnIdentifer))
		{
			gridValues.sort(new AgeComparator(form.getLocalContext().getSortOrderAge()));
		}
		else if (COLESTDISCH.equals(columnIdentifer))
		{
			gridValues.sort(new EstimateDischargeComparator(form.getLocalContext().getSortOrderEstimatedDischage()));
		}
		
		else if (COLALERTS.equals(columnIdentifer))
		{
			gridValues.sort(new AlertsComparator(form.getLocalContext().getSortOrderAlerts()));
		}
		// Get selected record
		Object selectedValue = form.dyngrdCurrIP().getValue();
		
		// Refresh grid
		populateRecordsToGrid(gridValues);
		
		// Reselect value
		form.dyngrdCurrIP().setValue(selectedValue);
		
		updatePatientGlobalContextOnSelection(form.dyngrdCurrIP().getValue());
	}

	/**
	 * WDEV-13136
	 * Function used to populate values to grid 
	 */
	private void populateRecordsToGrid(STHKCurrentInpatientListVoCollection gridValues)
	{
		// Clear records from grid
		form.dyngrdCurrIP().getRows().clear();
		
		// Terminate function if the collection is null
		if (gridValues == null)
			return;
		
		// Add each record from collection to grid
		for (int i = 0; i < gridValues.size(); i++)
		{
			addNewDynamicGridRow(gridValues.get(i));
		}
	}


	/**
	 * WDEV-13136
	 * Function used to toggle sort order for specified column
	 */
	private void sortOrderToggle(Object columnIdentifer)
	{
		// Age column
		if (COLAGE.equals(columnIdentifer))
		{
			if (SortOrder.ASCENDING.equals(form.getLocalContext().getSortOrderAge()))
				form.getLocalContext().setSortOrderAge(SortOrder.DESCENDING);
			else
				form.getLocalContext().setSortOrderAge(SortOrder.ASCENDING);
		}
		else
		{
			form.getLocalContext().setSortOrderAge(null);
		}
		
		// Estimate discharge column
		if (COLESTDISCH.equals(columnIdentifer))
		{
			if (SortOrder.ASCENDING.equals(form.getLocalContext().getSortOrderEstimatedDischage()))
				form.getLocalContext().setSortOrderEstimatedDischage(SortOrder.DESCENDING);
			else
				form.getLocalContext().setSortOrderEstimatedDischage(SortOrder.ASCENDING);
			
		}
		else
		{
			form.getLocalContext().setSortOrderEstimatedDischage(null);
		}
		
		//Alert column
		//WDEV-18011 
		if (COLALERTS.equals(columnIdentifer))
		{
			if (SortOrder.ASCENDING.equals(form.getLocalContext().getSortOrderAlerts()))
				form.getLocalContext().setSortOrderAlerts(SortOrder.DESCENDING);
			else
				form.getLocalContext().setSortOrderAlerts(SortOrder.ASCENDING);
			
		}
		else
		{
			form.getLocalContext().setSortOrderAlerts(null);
		}
	}


	/**
	 * WDEV-13136
	 * Function used to retrieve records from grid
	 */
	private STHKCurrentInpatientListVoCollection populateRecordsFromGrid()
	{
		// Create collection to return
		STHKCurrentInpatientListVoCollection gridValues = new STHKCurrentInpatientListVoCollection();
		
		// Add each record from grid to collection
		for (int i = 0; i < form.dyngrdCurrIP().getRows().size(); i++)
		{
			DynamicGridRow row = form.dyngrdCurrIP().getRows().get(i);
			
			if (row.getValue() instanceof STHKCurrentInpatientListVo)
				gridValues.add((STHKCurrentInpatientListVo) row.getValue());
		}
		
		// Return collection grid
		return gridValues;
	}


	private void loadHospitals() 
	{
		LocationLiteVoCollection voColl = domain.listActiveHospitalsLite();
		for (int i = 0 ; voColl != null && i < voColl.size() ; i++)
		{
			form.cmbHospital().newRow(voColl.get(i), voColl.get(i).getName());

			if (engine.getCurrentLocation() != null 
				&& voColl.get(i).getID_Location().equals(engine.getCurrentLocation().getID()))
				form.cmbHospital().setValue(voColl.get(i));
		}
	}

	private void refreshSearchCriteria()
	{
		CurrentInpatientListFilterVo voFilter = form.getGlobalContext().STHK.getCurrentInpatientListFilter();
		
		if (voFilter != null)
		{
			form.cmbIDType().setValue(voFilter.getIDType());
			form.txtIDNum().setValue(voFilter.getHospNum());
			form.txtSurname().setValue(voFilter.getSurname());
			form.txtForename().setValue(voFilter.getForename());
			
			form.cmbHospital().setValue(null);
			for(int i = 0 ; voFilter.getHospitalIsNotNull() && i < form.cmbHospital().getValues().size() ; i++)
			{
				LocationLiteVo voHosp = (LocationLiteVo)form.cmbHospital().getValues().get(i);
				if (voHosp.getID_Location().equals(voFilter.getHospital().getID_Location()))
					form.cmbHospital().setValue((LocationLiteVo)form.cmbHospital().getValues().get(i));
			}
			if ( form.cmbHospital().getValue() == null && voFilter.getHospitalIsNotNull())
			{
				LocationLiteVo voHosp = domain.getHospital(voFilter.getHospital());
				form.cmbHospital().newRow(voHosp, voHosp.getName().toString());
				form.cmbHospital().setValue(voHosp);
			}

			for(int i = 0 ; voFilter.getConsultantIsNotNull() && i < form.qmbConsultant().getValues().size() ; i++)
			{
				HcpLiteVo voHCP = (HcpLiteVo)form.qmbConsultant().getValues().get(i);
				if (voHCP.getID_Hcp().equals(voFilter.getConsultant().getID_Hcp()))
					form.qmbConsultant().setValue((HcpLiteVo)form.qmbConsultant().getValues().get(i));
			}

			form.cmbSideWard().setValue(voFilter.getSideWard());
			for(int i = 0 ; voFilter.getConsultantIsNotNull() && i < form.qmbConsultant().getValues().size() ; i++)
			{
				HcpLiteVo voHCP = (HcpLiteVo)form.qmbConsultant().getValues().get(i);
				if (voHCP.getID_Hcp().equals(voFilter.getConsultant().getID_Hcp()))
					form.qmbConsultant().setValue((HcpLiteVo)form.qmbConsultant().getValues().get(i));
			}
			if ( form.qmbConsultant().getValue() == null && voFilter.getConsultantIsNotNull())
			{
				HcpLiteVo voHCP = domain.getHCP(voFilter.getConsultant().getID_Hcp());
				form.qmbConsultant().newRow(voHCP, voHCP.getMos().getName().toString());
				form.qmbConsultant().setValue(voHCP);
			}
			
			form.qmbWard().setValue(null);
			for(int i = 0 ; voFilter.getWardIsNotNull() && i < form.qmbWard().getValues().size() ; i++)
			{
				LocationLiteVo voWard = (LocationLiteVo)form.qmbWard().getValues().get(i);
				if (voWard.getID_Location().equals(voFilter.getWard().getID_Location()))
					form.qmbWard().setValue((LocationLiteVo)form.qmbWard().getValues().get(i));
			}
			if ( form.qmbWard().getValue() == null&& voFilter.getWardIsNotNull())
			{
				LocationLiteVo voWard = domain.getWard(voFilter.getWard());
				form.qmbWard().newRow(voWard, voWard.getName());
				form.qmbWard().setValue(voWard);
			}
			form.cmbAlert().setValue(voFilter.getAlert());
					
			search(true);
		}
	}
	
	private void search(boolean fromButton) 
	{
		STHKCurrentInpatientListVo selectedRecord = null;
		if(!fromButton && form.dyngrdCurrIP().getValue() instanceof STHKCurrentInpatientListVo)
		{
			selectedRecord = (STHKCurrentInpatientListVo) form.dyngrdCurrIP().getValue();
		}
		
		form.dyngrdCurrIP().getRows().clear();
		form.lblTotal().setValue("Total : 0"); //WDEV-18099
		form.getContextMenus().hideAllGenericGridMenuItems();
		
		CurrentInpatientListFilterVo voFilter = new CurrentInpatientListFilterVo();
		voFilter.setIDType(form.cmbIDType().getValue());
		voFilter.setHospNum(form.txtIDNum().getValue());
		
		if (form.txtIDNum().getValue() != null
			&& form.cmbIDType().getValue() == null)
		{
			engine.showErrors(new String[]{"Please enter both an Identifier type as well as its value."});
			return;
		}

		//WDEV-13065 -- if (form.txtIDNum().getValue() == null)

		voFilter.setSurname(form.txtSurname().getValue());
		voFilter.setForename(form.txtForename().getValue());
		voFilter.setWard(form.qmbWard().getValue());
		voFilter.setSideWard(form.cmbSideWard().getValue());
		voFilter.setConsultant(form.qmbConsultant().getValue());
		voFilter.setAlert(form.cmbAlert().getValue());
		voFilter.setHospital(form.cmbHospital().getValue());

		form.getGlobalContext().STHK.setCurrentInpatientListFilter(voFilter);

		STHKCurrentInpatientListVoCollection voColl;
		if (voFilter.countFieldsWithValue() == 1 
			&& voFilter.getIDTypeIsNotNull()) 
		{
			engine.showMessage("Please enter some valid search criteria.", "Invalid search cirteria", MessageButtons.OK, MessageIcon.ERROR);
			return;
		}

		if (voFilter.countFieldsWithValue() > 0)
		{
			voColl = domain.listCurrentInpatients(voFilter);
		}
		else
		{
			engine.showErrors(new String[]{"Please enter some search criteria."});
			return;
		}
		
		if (voColl == null || voColl.size() == 0)
		{
			engine.showMessage("No matching records found");
			return;
		}
		if (voColl != null)
			form.lblTotal().setValue("Total : " + String.valueOf(voColl.size()));
			
		
		for (int i = 0 ; voColl != null && i < voColl.size() ; i++)
		{
			addNewDynamicGridRow(voColl.get(i));
		}
		
		form.dyngrdCurrIP().setValue(selectedRecord);
	}

	private void initialize() 
	{
		loadAlerts();
		initializeDynamicGrid();
	
		form.cmbIDType().setValue(PatIdType.getNegativeInstance(ConfigFlag.UI.DISPLAY_PATID_TYPE.getValue()));

		loadHospitals();
		if (form.cmbHospital().getValue() == null && engine.getCurrentLocation() != null)
		{
			//Try and load the logged in location as a ward
			LocMostVo voLoc = domain.getLocation((LocationRefVo)engine.getCurrentLocation());
			if (voLoc != null && voLoc.getTypeIsNotNull()
				&& voLoc.getType().equals(LocationType.WARD))
			{
				form.cmbHospital().setValue(voLoc.getParentLocation());
	
				form.qmbWard().newRow(voLoc, voLoc.getName());
				form.qmbWard().setValue(voLoc);
			}
		}
	}

	private void loadAlerts()
	{
		form.cmbAlert().clear();
		
		TreeNode[] coll = LookupHelper.getAlertType(domain.getLookupService()).getRootNodes();
		
		if(coll != null)
		{
			for(int i = 0 ; i < coll.length ; i++)
			{
				AlertType item = (AlertType)coll[i];
				ArrayList<LookupInstVo> coll1 = item.getChildInstances();

				AlertType type = null;
				for(int j=0 ; j < coll1.size() ; j++)
				{
					type = (AlertType)coll1.get(j);
					if(type.isActive())
						form.cmbAlert().newRow((AlertType)coll1.get(j), coll1.get(j).toString());
				}
			}
		}
	}

	@Override
	protected void onQmbWardTextSubmited(String value) throws ims.framework.exceptions.PresentationLogicException
	{
		if (form.cmbHospital().getValue() == null)
		{
			engine.showMessage("Please select a Hospital to find a Ward for.");
			return;
		}
		
		if (value != null)
		{
			LocationLiteVoCollection voColl = domain.listWards(form.cmbHospital().getValue().getID_Location(), value);

			voColl.sort();
			form.qmbWard().clear();
			for (int i = 0; i < voColl.size(); i++)
				form.qmbWard().newRow(voColl.get(i), voColl.get(i).getName());

			if (voColl.size() == 1)
				form.qmbWard().setValue(voColl.get(0));
			else if (voColl.size() > 1)
				form.qmbWard().showOpened();
		}
	}
	@Override
	protected void onQmbConsultantTextSubmited(String value) throws ims.framework.exceptions.PresentationLogicException
	{
		if (value != null)
		{
			HcpFilter voHCPFilter = new HcpFilter();
			PersonName name = new PersonName();
			name.setSurname(value);
			voHCPFilter.setQueryName(name);
			voHCPFilter.setHcpType(HcpDisType.MEDICAL);
			
			HcpLiteVoCollection voColl = domain.listHCPs(voHCPFilter);

			voColl.sort();
			form.qmbConsultant().clear();
			for (int i = 0; i < voColl.size(); i++)
			{
				form.qmbConsultant().newRow(voColl.get(i), voColl.get(i).getName().toString());
			}
			if (voColl.size() == 1)
				form.qmbConsultant().setValue(voColl.get(0));
			else if (voColl.size() > 1)
				form.qmbConsultant().showOpened();
		}
	}

	@Override
	protected void onImbClearClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.cmbHospital().setValue(null);
		form.cmbAlert().setValue(null);
		form.cmbSideWard().setValue(null);
		
		form.qmbConsultant().clear();

		form.qmbWard().clear();
		
		form.txtForename().setValue(null);
		form.txtSurname().setValue(null);
		form.txtIDNum().setValue(null);
		
		form.dyngrdCurrIP().getRows().clear();
		form.lblTotal().setValue("Total : 0");
		form.getGlobalContext().STHK.setCurrentInpatientListFilter(null);
		form.getGlobalContext().Core.setCurrentInPatientListWard(null);
		
		form.getContextMenus().hideAllGenericGridMenuItems();

//		updateContextMenus();

		//Clear the selected patient information in the Engine
		form.getGlobalContext().Core.setSelectingPatientForm(null);
 		form.getGlobalContext().Core.setPatientShort(null);
		form.getGlobalContext().Core.setPatientToBeDisplayed(null);
		engine.setPatientInfo("Please enter Patient ID or Surname and/or Forename");

	}

	@Override
	protected void onImbSearchClick() throws ims.framework.exceptions.PresentationLogicException
	{
		search(true);
	}

	@Override
	protected void onCmbHospitalValueChanged() throws PresentationLogicException 
	{
		form.qmbWard().clear();
	}

	public void refresh() 
	{
		if (form.getGlobalContext().STHK.getCurrentInpatientListFilterIsNotNull())
			refreshSearchCriteria();
	}

	@Override
	protected void onContextMenuItemClick(int menuItemID, Control sender) throws PresentationLogicException 
	{
		switch (menuItemID)
		{
		case GenForm.ContextMenus.GenericGrid.Add:
			addEditComment(null);
		break;
		case GenForm.ContextMenus.GenericGrid.Update:
			viewWard();
		break;
		}
	}

	private void viewWard()
	{
		if (((STHKCurrentInpatientListVo)form.dyngrdCurrIP().getSelectedRow().getValue()).getPasEventIsNotNull() )
			form.getGlobalContext().Core.setCurrentInPatientListWard(((STHKCurrentInpatientListVo)form.dyngrdCurrIP().getSelectedRow().getValue()).getPasEvent().getLocation());
		
		engine.open(form.getForms().Core.WardViewDialog);
	}

	private void addEditComment(DynamicGridCell cell) 
	{
		if (cell != null)
			form.dyngrdCurrIP().setSelectedRow(cell.getRow());

		if (form.dyngrdCurrIP().getSelectedRow() != null
			&& form.dyngrdCurrIP().getSelectedRow().getValue() != null)
		{
			STHKCurrentInpatientListVo voIP = domain.getCurrentIPRecord(((STHKCurrentInpatientListVo)form.dyngrdCurrIP().getSelectedRow().getValue()));
			form.getGlobalContext().Core.setCommentDialogString(voIP.getComments());
			form.dyngrdCurrIP().getSelectedRow().setValue(voIP);
			updatePatientGlobalContextOnSelection(voIP); //WDEV-18065 
			engine.open(form.getForms().Core.CommentDialog);
		}
	}

	@Override
	protected void onFormDialogClosed(FormName formName, DialogResult result) throws PresentationLogicException 
	{
		if(formName.equals(form.getForms().Core.CommentDialog) && result.equals(DialogResult.OK))
		{
			STHKCurrentInpatientListVo voIP = ((STHKCurrentInpatientListVo)form.dyngrdCurrIP().getSelectedRow().getValue());
			voIP.setComments(form.getGlobalContext().Core.getCommentDialogString());
			
			String [] errors = voIP.validate();
			if (errors != null)
			{
				engine.showErrors(errors);
				return;
			}
			
			try 
			{
				domain.saveIP(voIP);
			} 
			catch (DomainInterfaceException e) 
			{
				engine.showMessage(e.getMessage());
				return;
			} 
			catch (StaleObjectException e) 
			{
				engine.showMessage(ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			}
			search(false);
		}
		else if (formName.equals(form.getForms().Core.WardViewDialog))//WDEV-14485
		{
			search(false);
		}
		updateContextMenus(); //WDEV-18088
	}

	private void initializeDynamicGrid() 
	{
		form.dyngrdCurrIP().clear();
		form.dyngrdCurrIP().setSelectable(true);
		
		DynamicGridColumn surnameColumn = form.dyngrdCurrIP().getColumns().newColumn("Surname", COLSURNAME);
		surnameColumn.setSortMode(SortMode.AUTOMATIC);
		surnameColumn.setWidth(68);	

		DynamicGridColumn forenameColumn = form.dyngrdCurrIP().getColumns().newColumn("Forename", COLFORENAME);
		forenameColumn.setSortMode(SortMode.AUTOMATIC);
		forenameColumn.setWidth(68);	
			
		DynamicGridColumn hospnumColumn = form.dyngrdCurrIP().getColumns().newColumn("Hosp. No.", COLHOSPNUM);
		hospnumColumn.setSortMode(SortMode.AUTOMATIC);
		hospnumColumn.setWidth(65);
			
		DynamicGridColumn ageColumn = form.dyngrdCurrIP().getColumns().newColumn("Age", COLAGE);
		ageColumn.setSortMode(SortMode.MANUAL);
		ageColumn.setWidth(35);
			
		DynamicGridColumn sexColumn = form.dyngrdCurrIP().getColumns().newColumn("Sex", COLSEX);
		sexColumn.setSortMode(SortMode.AUTOMATIC);
		sexColumn.setWidth(45);
			
		DynamicGridColumn bedColumn = form.dyngrdCurrIP().getColumns().newColumn("Bed Type", COLBEDTYPE);
		bedColumn.setSortMode(SortMode.AUTOMATIC);
		bedColumn.setWidth(65);
			
		DynamicGridColumn alertColumn = form.dyngrdCurrIP().getColumns().newColumn("Alert", COLALERTS);
		alertColumn.setSortMode(SortMode.MANUAL);
		alertColumn.setWidth(45);
			
		DynamicGridColumn wardColumn = form.dyngrdCurrIP().getColumns().newColumn("Ward", COLWARD);
		wardColumn.setSortMode(SortMode.AUTOMATIC);
		wardColumn.setWidth(77);
			
		DynamicGridColumn locColumn = form.dyngrdCurrIP().getColumns().newColumn("Main/Side", COLLOCATION);
		locColumn.setSortMode(SortMode.AUTOMATIC);
		locColumn.setWidth(77);
			
		DynamicGridColumn consColumn = form.dyngrdCurrIP().getColumns().newColumn("Consultant", COLCONSULANT);
		consColumn.setSortMode(SortMode.AUTOMATIC);
		consColumn.setWidth(77);
			
		DynamicGridColumn estDateColumn = form.dyngrdCurrIP().getColumns().newColumn("Est. Disch", COLESTDISCH);
		estDateColumn.setSortMode(SortMode.MANUAL);
		estDateColumn.setWidth(65);
			
		DynamicGridColumn commentsColumn = form.dyngrdCurrIP().getColumns().newColumn("Comments", COLCOMMENT);
		 
		if(ConfigFlag.UI.BED_INFO_UI_TYPE.getValue().equals("CCO"))
		{			
			commentsColumn.setWidth(90);	

			DynamicGridColumn hlColumn = form.dyngrdCurrIP().getColumns().newColumn("HL", COLHOMELEAVE);
			hlColumn.setSortMode(SortMode.AUTOMATIC);
			hlColumn.setWidth(-1);	
		}
		else
			commentsColumn.setWidth(-1);	

		form.lblTotal().setValue("Total : 0");		
	}
	
	private void addNewDynamicGridRow(STHKCurrentInpatientListVo voCurrInpat) 
	{
		if (voCurrInpat == null)
			return;
		
		voCurrInpat.setActiveAlerts(false);
		
		DynamicGridRow newRow = form.dyngrdCurrIP().getRows().newRow();
		if (voCurrInpat.getPasEventIsNotNull() && voCurrInpat.getPasEvent().getPatientIsNotNull())
			voCurrInpat.getPasEvent().getPatient().calculateAge();
		
		if (voCurrInpat.getPasEventIsNotNull() && voCurrInpat.getPasEvent().getPatientIsNotNull() && voCurrInpat.getPasEvent().getPatient().getNameIsNotNull())
		{
			DynamicGridCell snameCell = newRow.getCells().newCell(getColumn(COLSURNAME), DynamicCellType.LABEL);
			snameCell.setValue(voCurrInpat.getPasEvent().getPatient().getName().getSurname());
			snameCell.setReadOnly(true);
			
			DynamicGridCell fnameCell = newRow.getCells().newCell(getColumn(COLFORENAME), DynamicCellType.LABEL);
			fnameCell.setValue(voCurrInpat.getPasEvent().getPatient().getName().getForename());
			fnameCell.setReadOnly(true);	
		}	
		
		if (voCurrInpat.getPasEventIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatientIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatient().getIdentifiersIsNotNull()
			&& voCurrInpat.getPasEvent().getPatient().getIdentifiers().size() > 0
			&& voCurrInpat.getPasEvent().getPatient().getPatId(PatIdType.HOSPNUM) != null
			&& voCurrInpat.getPasEvent().getPatient().getPatId(PatIdType.HOSPNUM).getValueIsNotNull())
		{
			DynamicGridCell hospnumCell = newRow.getCells().newCell(getColumn(COLHOSPNUM), DynamicCellType.LABEL);
			hospnumCell.setValue(voCurrInpat.getPasEvent().getPatient().getPatId(PatIdType.HOSPNUM).getValueIsNotNull() ? voCurrInpat.getPasEvent().getPatient().getPatId(PatIdType.HOSPNUM).getValue().toString(): "");
			hospnumCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getPasEventIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatientIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatient().getAgeIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLAGE), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getPasEvent().getPatient().getAge().toString());
			patientCell.setReadOnly(true);
		}	

		if (voCurrInpat.getPasEventIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatientIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatient().getSexIsNotNull() )
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLSEX), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getPasEvent().getPatient().getSex().toString());
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getPasEventIsNotNull() 
			&& voCurrInpat.getPasEvent().getPatientIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLALERTS), DynamicCellType.IMAGE);// WDEV-18011 
			//wdev-11146
			if(voCurrInpat.getPasEvent().getPatient().getPatientAlertsIsNotNull() && voCurrInpat.getPasEvent().getPatient().getPatientAlerts().size() > 0 )
			{
				boolean tempBool = false;
				for(int i = 0; i < voCurrInpat.getPasEvent().getPatient().getPatientAlerts().size();i++)
				{
					PatientAlertLiteVo patAlertLiteVo = voCurrInpat.getPasEvent().getPatient().getPatientAlerts().get(i);
					if(patAlertLiteVo != null && patAlertLiteVo.getIsCurrentlyActiveAlert().equals(Boolean.TRUE))
					{
						tempBool = true;
						break;
					}
				}
				
				if (tempBool)
				{
					patientCell.setValue(form.getImages().Core.Alert16); // WDEV-18011
					voCurrInpat.setActiveAlerts(true);
					
				}
			}
			//----------
			
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getPasEventIsNotNull() && voCurrInpat.getPasEvent().getLocationIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLWARD), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getPasEvent().getLocation().getName().toString());
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getWardTypeIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLLOCATION), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getWardType().toString());
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getBedIsNotNull() 
			&& voCurrInpat.getBed().getBedSpaceIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLBEDTYPE), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getBed().getBedSpace().getBedSpaceType().toString());
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getPasEventIsNotNull() 
			&& voCurrInpat.getPasEvent().getConsultantIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLCONSULANT), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getPasEvent().getConsultant().getName().toString());
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getEstDischargeDateIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLESTDISCH), DynamicCellType.LABEL);
			patientCell.setValue(voCurrInpat.getEstDischargeDate().toString());
			patientCell.setReadOnly(true);
		}	
		
		if (voCurrInpat.getCommentsIsNotNull())
		{
			DynamicGridCell patientCell = newRow.getCells().newCell(getColumn(COLCOMMENT), DynamicCellType.IMAGEBUTTON);
			patientCell.setReadOnly(false);
			patientCell.setAutoPostBack(true);
			patientCell.setValue(form.getImages().Core.Memo);
			patientCell.setTooltip(voCurrInpat.getComments());
		}	
		//wdev-13509
		if (voCurrInpat.getIsOnHomeLeaveIsNotNull()
			&& voCurrInpat.getIsOnHomeLeave())
		{
			DynamicGridCell hlCell = newRow.getCells().newCell(getColumn(COLHOMELEAVE), DynamicCellType.LABEL);
			hlCell.setReadOnly(false);
			hlCell.setAutoPostBack(true);
			hlCell.setValue("(H)");
			
			StringBuffer sb = new StringBuffer();
			sb.append(voCurrInpat.getExpectedDateOfReturnIsNotNull() ? "Expected Return : " + voCurrInpat.getExpectedDateOfReturn().toString() : "");
			sb.append(voCurrInpat.getExpectedTimeOfReturnIsNotNull() ? " at " + voCurrInpat.getExpectedTimeOfReturn().toString() : "");
			
			hlCell.setTooltip(sb.toString());
		}	
		
		if (voCurrInpat.getEstDischargeDateIsNotNull())
		{
			// WDEV-13136
			// Use clone when calling addDay() - or similar - function to prevent alteration of date
			if (voCurrInpat.getEstDischargeDate().isLessOrEqualThan(new Date())
				&& ((Date)voCurrInpat.getEstDischargeDate().clone()).addDay(1).isGreaterOrEqualThan(new Date()))
				newRow.setBackColor(Color.Orange);

			if(voCurrInpat.getIsConfirmedDischargeIsNotNull()
				&& voCurrInpat.getIsConfirmedDischarge())
				newRow.setBackColor(Color.Red);
					
			if (voCurrInpat.getPasEventIsNotNull()
				&& voCurrInpat.getPasEvent().getPatientIsNotNull())
			{
				if(voCurrInpat.getPasEvent().getPatient().getAssociatedPatientIsNotNull())
				{
					newRow.setBackColor(ConfigFlag.UI.MERGED_COLOUR.getValue());
					newRow.setTextColor(Color.Orange);
				}
						
				if (voCurrInpat.getPasEvent().getPatient().getDodIsNotNull())
				{
					newRow.setBackColor(ConfigFlag.UI.RIP_COLOUR.getValue());			
					newRow.setTextColor(Color.Orange);
				}
			}
		}
		
//		Each row in the list will be assigned a background colour according to the bed status: 
//			White. Occupied. Estimated date/time of discharge >24hrs from current date/time 
//			Amber. Occupied. Estimated date/time of discharge <24hrs from current date/time 
//			Red. Occupied. Confirmed discharge 

		if (voCurrInpat.getPasEventIsNotNull()
				&& voCurrInpat.getPasEvent().getPatientIsNotNull())
		{
			if(voCurrInpat.getPasEvent().getPatient().getAssociatedPatientIsNotNull())
				newRow.setBackColor(ConfigFlag.UI.MERGED_COLOUR.getValue());
			
			if (voCurrInpat.getPasEvent().getPatient().getDodIsNotNull())
				newRow.setBackColor(ConfigFlag.UI.RIP_COLOUR.getValue());
		}

		
		newRow.setValue(voCurrInpat);
	}

	private DynamicGridColumn getColumn(Integer identifier)
	{
		return form.dyngrdCurrIP().getColumns().getByIdentifier(identifier);
	}

	@Override
	protected void onDyngrdCurrIPCellButtonClicked(DynamicGridCell cell) 
	{
		addEditComment(cell);
	}

	@Override
	protected void onDyngrdCurrIPRowSelectionChanged(DynamicGridRow row) 
	{
		if (row == null)
			updatePatientGlobalContextOnSelection(null);
		else
			updatePatientGlobalContextOnSelection(row.getValue());
		
		updateContextMenus();
	}


	/**
	 * WDEV-13136
	 * Function used to update global context for PatientShort and PatientToBeDisplayed when
	 * grid selection changes
	 */
	private void updatePatientGlobalContextOnSelection(Object value)
	{
		form.getGlobalContext().Core.setSelectingPatientForm(null);
		form.getGlobalContext().Core.setPatientToBeDisplayed(null);
		form.getGlobalContext().Core.setPatientShort(null);

		if (value == null)
			return;
			
		if ((value instanceof STHKCurrentInpatientListVo)
				&& ((STHKCurrentInpatientListVo) value).getPasEventIsNotNull()
				&& ((STHKCurrentInpatientListVo) value).getPasEvent().getPatientIsNotNull())
		{
			PatientShort patient = domain.getPatientShort(((STHKCurrentInpatientListVo) value).getPasEvent().getPatient());
		
			form.getGlobalContext().Core.setSelectingPatientForm(engine.getFormName());
			form.getGlobalContext().Core.setPatientToBeDisplayed(patient);
			form.getGlobalContext().Core.setPatientShort(patient);
		}
	}

	/**
	 * WDEV-13136
	 * Function used to update context menu options state
	 * Fixed null pointer exception
	 */
	private void updateContextMenus()
	{
		form.getContextMenus().hideAllGenericGridMenuItems();

		form.getContextMenus().getGenericGridAddItem().setText("Add/Edit Comment");
		form.getContextMenus().getGenericGridAddItem().setVisible(form.dyngrdCurrIP().getValue() != null);

		
		boolean selectedRecordHasWard = form.dyngrdCurrIP().getSelectedRow() != null
										&& form.dyngrdCurrIP().getSelectedRow().getCells().get(getColumn(COLWARD)) != null 
										&& form.dyngrdCurrIP().getSelectedRow().getCells().get(getColumn(COLWARD)).getValue() != null;
		
		form.getContextMenus().getGenericGridUpdateItem().setText("Ward View");
		form.getContextMenus().getGenericGridUpdateItem().setVisible(selectedRecordHasWard);
	}

}