package com.flowingcode.addons.applayout.endpoint;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import dev.hilla.Nonnull;

public class MenuItemDto {

    private String label;
    @Nullable
    private String href;
    private @Nonnull List<@Nonnull MenuItemDto> children = new ArrayList<>();

    public MenuItemDto(String label, List<MenuItemDto> children) {
        this(label,null,children);
    }

    public MenuItemDto(String label, String href) {
        this(label,href,new ArrayList<>());
    }

    public MenuItemDto(String label, String href, List<MenuItemDto> children) {
        this.label = label;
        this.href = href;
        this.children = children;
    }

    public List<MenuItemDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItemDto> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }
    public String getHref() {
        return href;
    }
    public void setHref(@Nonnull String href) {
        this.href = href;
    }
    public void setLabel(@Nonnull String label) {
        this.label = label;
    }

}
