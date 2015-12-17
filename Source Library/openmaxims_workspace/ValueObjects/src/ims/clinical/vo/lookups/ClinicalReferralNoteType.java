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

package ims.clinical.vo.lookups;

import ims.framework.cn.data.TreeNode;
import java.util.ArrayList;
import ims.framework.utils.Image;
import ims.framework.utils.Color;

public class ClinicalReferralNoteType extends ims.vo.LookupInstVo implements TreeNode
{
	private static final long serialVersionUID = 1L;

	public ClinicalReferralNoteType()
	{
		super();
	}
	public ClinicalReferralNoteType(int id)
	{
		super(id, "", true);
	}
	public ClinicalReferralNoteType(int id, String text, boolean active)
	{
		super(id, text, active, null, null, null);
	}
	public ClinicalReferralNoteType(int id, String text, boolean active, ClinicalReferralNoteType parent, Image image)
	{
		super(id, text, active, parent, image);
	}
	public ClinicalReferralNoteType(int id, String text, boolean active, ClinicalReferralNoteType parent, Image image, Color color)
	{
		super(id, text, active, parent, image, color);
	}
	public ClinicalReferralNoteType(int id, String text, boolean active, ClinicalReferralNoteType parent, Image image, Color color, int order)
	{
		super(id, text, active, parent, image, color, order);
	}
	public static ClinicalReferralNoteType buildLookup(ims.vo.LookupInstanceBean bean)
	{
		return new ClinicalReferralNoteType(bean.getId(), bean.getText(), bean.isActive());
	}
	public String toString()
	{
		if(getText() != null)
			return getText();
		return "";
	}
	public TreeNode getParentNode()
	{
		return (ClinicalReferralNoteType)super.getParentInstance();
	}
	public ClinicalReferralNoteType getParent()
	{
		return (ClinicalReferralNoteType)super.getParentInstance();
	}
	public void setParent(ClinicalReferralNoteType parent)
	{
		super.setParentInstance(parent);
	}
	public TreeNode[] getChildren()
	{
		ArrayList children = super.getChildInstances();
		ClinicalReferralNoteType[] typedChildren = new ClinicalReferralNoteType[children.size()];
		for (int i = 0; i < children.size(); i++)
		{
			typedChildren[i] = (ClinicalReferralNoteType)children.get(i);
		}
		return typedChildren;
	}
	public int addChild(TreeNode child)
	{
		if (child instanceof ClinicalReferralNoteType)
		{
			super.addChild((ClinicalReferralNoteType)child);
		}
		return super.getChildInstances().size();
	}
	public int removeChild(TreeNode child)
	{
		if (child instanceof ClinicalReferralNoteType)
		{
			super.removeChild((ClinicalReferralNoteType)child);
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
		ClinicalReferralNoteTypeCollection result = new ClinicalReferralNoteTypeCollection();
		result.add(FURTHER_INFORMATION);
		result.add(SECOND_OPINION);
		result.add(BOOKING_NOTE);
		result.add(COMPLETION_NOTE);
		result.add(FURTHER_INFORMATION_REQUIRED);
		result.add(ADMIN_EVENT);
		result.add(REFERRAL_REJECTION);
		return result;
	}
	public static ClinicalReferralNoteType[] getNegativeInstances()
	{
		ClinicalReferralNoteType[] instances = new ClinicalReferralNoteType[7];
		instances[0] = FURTHER_INFORMATION;
		instances[1] = SECOND_OPINION;
		instances[2] = BOOKING_NOTE;
		instances[3] = COMPLETION_NOTE;
		instances[4] = FURTHER_INFORMATION_REQUIRED;
		instances[5] = ADMIN_EVENT;
		instances[6] = REFERRAL_REJECTION;
		return instances;
	}
	public static String[] getNegativeInstanceNames()
	{
		String[] negativeInstances = new String[7];
		negativeInstances[0] = "FURTHER_INFORMATION";
		negativeInstances[1] = "SECOND_OPINION";
		negativeInstances[2] = "BOOKING_NOTE";
		negativeInstances[3] = "COMPLETION_NOTE";
		negativeInstances[4] = "FURTHER_INFORMATION_REQUIRED";
		negativeInstances[5] = "ADMIN_EVENT";
		negativeInstances[6] = "REFERRAL_REJECTION";
		return negativeInstances;
	}
	public static ClinicalReferralNoteType getNegativeInstance(String name)
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
	public static ClinicalReferralNoteType getNegativeInstance(Integer id)
	{
		if(id == null)
			return null;
		ClinicalReferralNoteType[] negativeInstances = getNegativeInstances();
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
	public static final int TYPE_ID = 1231138;
	public static final ClinicalReferralNoteType FURTHER_INFORMATION = new ClinicalReferralNoteType(-2718, "Further Information", true, null, null, Color.Default);
	public static final ClinicalReferralNoteType SECOND_OPINION = new ClinicalReferralNoteType(-2720, "Second opinion Note", true, null, null, Color.Default);
	public static final ClinicalReferralNoteType BOOKING_NOTE = new ClinicalReferralNoteType(-2721, "Booking Note", true, null, null, Color.Default);
	public static final ClinicalReferralNoteType COMPLETION_NOTE = new ClinicalReferralNoteType(-2722, "Completion Note", true, null, null, Color.Default);
	public static final ClinicalReferralNoteType FURTHER_INFORMATION_REQUIRED = new ClinicalReferralNoteType(-2724, "Further Information required", true, null, null, Color.Default);
	public static final ClinicalReferralNoteType ADMIN_EVENT = new ClinicalReferralNoteType(-2756, "Admin Event", true, null, null, Color.Default);
	public static final ClinicalReferralNoteType REFERRAL_REJECTION = new ClinicalReferralNoteType(-3377, "Referral Rejection", true, null, null, Color.Default);
}