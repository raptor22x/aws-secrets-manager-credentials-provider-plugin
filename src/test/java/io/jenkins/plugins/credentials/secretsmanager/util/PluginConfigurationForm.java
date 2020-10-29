package io.jenkins.plugins.credentials.secretsmanager.util;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PluginConfigurationForm {

    private final HtmlForm form;

    public PluginConfigurationForm(HtmlForm form) {
        this.form = form;
    }

    public Optional<String> getValidateSuccessMessage() {
        return form.getElementsByAttribute("div", "class", "ok")
                .stream()
                .map(DomNode::getTextContent)
                .filter(msg -> !msg.equalsIgnoreCase("Without a resource root URL, resources will be served from the main domain with Content-Security-Policy set."))
                .findFirst();
    }

    public String getValidateErrorMessage() {
        return form.getOneHtmlElementByAttribute("div", "class", "error").getTextContent();
    }

    public List<HtmlButton> getRepeatableAddButtons(String settingName) {
        return form.getByXPath(String.format("//td[contains(text(), '%s')]/following-sibling::td[@class='setting-main']//span[contains(string(@class),'repeatable-add')]//button[contains(text(), 'Add')]", settingName));
    }

    public List<HtmlSelect> getDropdownDescriptorSelectors(String settingName) {
        return form.getByXPath(String.format("//td[contains(string(@class),'setting-name') and text()='%s']/following-sibling::td[contains(string(@class),'setting-main')]/select[contains(string(@class),'dropdownList')]", settingName));
    }

    public List<HtmlButton> getValidateButtons(String textContent) {
        return form.getByXPath("//span[contains(string(@class),'validate-button')]//button")
                .stream()
                .map(obj -> (HtmlButton) (obj))
                .filter(button -> button.getTextContent().equals(textContent))
                .collect(Collectors.toList());
    }
}
