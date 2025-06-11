package com.xiaoyou.admediationdemos.data.main;

/**
 * A {@link ListItem} representing a section header on the main screen
 */
public class SectionHeader
        implements ListItem
{
    private final String title;

    public SectionHeader(final String title)
    {
        this.title = title;
    }

    /**
     * @return The time of the section header.
     */
    public String getTitle()
    {
        return title;
    }

    @Override
    public int getType()
    {
        return TYPE_SECTION_HEADER;
    }
}