package com.sporniket.libre.lang.html;

import static com.sporniket.strings.StringPredicates.IS_NOT_EMPTY;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Model for the a radio button.
 * 
 * <p>
 * To generate the HTML code, simply call the {@link #toString()} method.
 * </p>
 * 
 * <p>
 * &copy; Copyright 2002-2022 David Sporn
 * </p>
 * <hr>
 * 
 * <p>
 * This file is part of <i>The Sporniket Core Library &#8211; lang</i>.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * <p>
 * <i>The Sporniket Core Library &#8211; lang</i> is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * 
 * <p>
 * You should have received a copy of the GNU Lesser General Public License along with <i>The Sporniket Core Library &#8211;
 * lang</i>. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>. 2
 * 
 * <hr>
 * 
 * @author David SPORN
 * @version 22.09.01
 * @since 12.06.01
 */
public class RadioButton extends FormControl implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 4005024648499626894L;

    /**
     * Template for the code.
     * 
     * <ul>
     * <li>{0} : name</li>
     * <li>{1} : id</li>
     * <li>{2} : value</li>
     * <li>{3} : disabled</li>
     * <li>{4} : preselected option</li>
     * <li>{5} : for</li>
     * <li>{6} : label</li> </lu>
     */
    private static final MessageFormat FORMAT_RADIO_BUTTON = new MessageFormat(
            "<input type=\"radio\" {0} {1} {2} {3} {4}/><label {5}>{6}</label>");

    /**
     * Create a fully specified radio button.
     * 
     * @param name
     *            name of the radio button.
     * @param value
     *            value of the radio button.
     * @param idSuffix
     *            suffix to add to compute the id attribute.
     * @param label
     *            label of the button.
     * @param isSelected
     *            selected attribute.
     * @param isDisabled
     *            disabled attribute.
     * @return a fully defined {@link RadioButton}.
     */
    public static RadioButton create(String name, String value, String idSuffix, String label, boolean isSelected,
            boolean isDisabled)
    {
        RadioButton _result = new RadioButton();
        _result.setName(name);
        _result.setValue(value);
        if (IS_NOT_EMPTY.test(idSuffix))
        {
            _result.setIdSuffix(idSuffix);
        }
        _result.setLabel(label);
        _result.setSelected(isSelected);
        _result.setDisabled(isDisabled);

        return _result;
    }

    /**
     * Label for the radio button.
     */
    private String myLabel;

    /**
     * Flag to pre-select the radio button.
     */
    private boolean mySelected;

    /**
     * @return the label
     */
    public String getLabel()
    {
        return myLabel;
    }

    /**
     * @return the selected
     */
    public boolean isSelected()
    {
        return mySelected;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label)
    {
        myLabel = label;
    }

    /**
     * @param selected
     *            the selected to set
     */
    public void setSelected(boolean selected)
    {
        mySelected = selected;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getHtmlCode();
    }

    /**
     * Generate the html code.
     * 
     * @return the HTML code.
     */
    public String getHtmlCode()
    {
        Object[] _params =
        {
                getHtmlCodeForNameAttribute(),
                getHtmlCodeForIdAttribute(),
                HtmlUtils.generateAttribute(HtmlUtils.AttributeNames.VALUE, getValue()),
                isSelected() ? HtmlUtils.SpecialAttribute.CHECKED : HtmlUtils.SpecialAttribute.NONE,
                isDisabled() ? HtmlUtils.SpecialAttribute.DISABLED : HtmlUtils.SpecialAttribute.NONE,
                HtmlUtils.generateAttribute(HtmlUtils.AttributeNames.Form.FOR, getId()),
                getLabel()

        };
        return FORMAT_RADIO_BUTTON.format(_params);
    }

}