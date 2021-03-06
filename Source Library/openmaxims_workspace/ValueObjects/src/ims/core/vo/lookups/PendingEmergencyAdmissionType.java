//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
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
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.core.vo.lookups;

import ims.framework.cn.data.TreeNode;
import java.util.ArrayList;
import ims.framework.utils.Image;
import ims.framework.utils.Color;

public class PendingEmergencyAdmissionType extends ims.vo.LookupInstVo implements TreeNode
{
	private static final long serialVersionUID = 1L;

	public PendingEmergencyAdmissionType()
	{
		super();
	}
	public PendingEmergencyAdmissionType(int id)
	{
		super(id, "", true);
	}
	public PendingEmergencyAdmissionType(int id, String text, boolean active)
	{
		super(id, text, active, null, null, null);
	}
	public PendingEmergencyAdmissionType(int id, String text, boolean active, PendingEmergencyAdmissionType parent, Image image)
	{
		super(id, text, active, parent, image);
	}
	public PendingEmergencyAdmissionType(int id, String text, boolean active, PendingEmergencyAdmissionType parent, Image image, Color color)
	{
		super(id, text, active, parent, image, color);
	}
	public PendingEmergencyAdmissionType(int id, String text, boolean active, PendingEmergencyAdmissionType parent, Image image, Color color, int order)
	{
		super(id, text, active, parent, image, color, order);
	}
	public static PendingEmergencyAdmissionType buildLookup(ims.vo.LookupInstanceBean bean)
	{
		return new PendingEmergencyAdmissionType(bean.getId(), bean.getText(), bean.isActive());
	}
	public String toString()
	{
		if(getText() != null)
			return getText();
		return "";
	}
	public TreeNode getParentNode()
	{
		return (PendingEmergencyAdmissionType)super.getParentInstance();
	}
	public PendingEmergencyAdmissionType getParent()
	{
		return (PendingEmergencyAdmissionType)super.getParentInstance();
	}
	public void setParent(PendingEmergencyAdmissionType parent)
	{
		super.setParentInstance(parent);
	}
	public TreeNode[] getChildren()
	{
		ArrayList children = super.getChildInstances();
		PendingEmergencyAdmissionType[] typedChildren = new PendingEmergencyAdmissionType[children.size()];
		for (int i = 0; i < children.size(); i++)
		{
			typedChildren[i] = (PendingEmergencyAdmissionType)children.get(i);
		}
		return typedChildren;
	}
	public int addChild(TreeNode child)
	{
		if (child instanceof PendingEmergencyAdmissionType)
		{
			super.addChild((PendingEmergencyAdmissionType)child);
		}
		return super.getChildInstances().size();
	}
	public int removeChild(TreeNode child)
	{
		if (child instanceof PendingEmergencyAdmissionType)
		{
			super.removeChild((PendingEmergencyAdmissionType)child);
		}
		return super.getChildInstances().size();
	}
	public Image getExpandedImage()
	{
		return super.getImage();
	}
	public Image getCollapsedImage()
	{
		return super.getImage();
	}
	public static ims.framework.IItemCollection getNegativeInstancesAsIItemCollection()
	{
		PendingEmergencyAdmissionTypeCollection result = new PendingEmergencyAdmissionTypeCollection();
		result.add(REQUEST_FOR_REPATRIATION);
		result.add(CLINICAL_NEED);
		result.add(GP);
		result.add(OTHER);
		return result;
	}
	public static PendingEmergencyAdmissionType[] getNegativeInstances()
	{
		PendingEmergencyAdmissionType[] instances = new PendingEmergencyAdmissionType[4];
		instances[0] = REQUEST_FOR_REPATRIATION;
		instances[1] = CLINICAL_NEED;
		instances[2] = GP;
		instances[3] = OTHER;
		return instances;
	}
	public static String[] getNegativeInstanceNames()
	{
		String[] negativeInstances = new String[4];
		negativeInstances[0] = "REQUEST_FOR_REPATRIATION";
		negativeInstances[1] = "CLINICAL_NEED";
		negativeInstances[2] = "GP";
		negativeInstances[3] = "OTHER";
		return negativeInstances;
	}
	public static PendingEmergencyAdmissionType getNegativeInstance(String name)
	{
		if(name == null)
			return null;
		String[] negativeInstances = getNegativeInstanceNames();
		for (int i = 0; i < negativeInstances.length; i++)
		{
			if(negativeInstances[i].equals(name))
				return getNegativeInstances()[i];
		}
		return null;
	}
	public static PendingEmergencyAdmissionType getNegativeInstance(Integer id)
	{
		if(id == null)
			return null;
		PendingEmergencyAdmissionType[] negativeInstances = getNegativeInstances();
		for (int i = 0; i < negativeInstances.length; i++)
		{
			if(negativeInstances[i].getID() == id)
				return negativeInstances[i];
		}
		return null;
	}
	public int getTypeId()
	{
		return TYPE_ID;
	}
	public static final int TYPE_ID = 1021380;
	public static final PendingEmergencyAdmissionType REQUEST_FOR_REPATRIATION = new PendingEmergencyAdmissionType(-3263, "Request for Repatriation", true, null, null, Color.Default);
	public static final PendingEmergencyAdmissionType CLINICAL_NEED = new PendingEmergencyAdmissionType(-3264, "Clinical Need", true, null, null, Color.Default);
	public static final PendingEmergencyAdmissionType GP = new PendingEmergencyAdmissionType(-3265, "GP", true, null, null, Color.Default);
	public static final PendingEmergencyAdmissionType OTHER = new PendingEmergencyAdmissionType(-3290, "Other", true, null, null, Color.Default);
}
