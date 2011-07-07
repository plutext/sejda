package org.sejda.core.manipulation.model.parameter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sejda.core.manipulation.model.pdf.transition.PdfPageTransition;

/**
 * Parameter class for the set pages transition manipulation.
 * 
 * @author Andrea Vacondio
 * 
 */
public class SetPagesTransitionParameters extends SinglePdfSourceParameters {

    @Valid
    private final Map<Integer, PdfPageTransition> transitions = new HashMap<Integer, PdfPageTransition>();
    @Valid
    private PdfPageTransition defaultTransition;
    private boolean fullScreen = false;

    public SetPagesTransitionParameters() {
        // no default transition
    }

    public SetPagesTransitionParameters(PdfPageTransition defaultTransition) {
        this.defaultTransition = defaultTransition;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     * Associates the given transition to the given page number. If a transition was already associated to the given page, it is replaced with the new one.
     * 
     * @param page
     * @param transition
     * @return the previously associated transition or null.
     */
    public PdfPageTransition putTransition(Integer page, PdfPageTransition transition) {
        return transitions.put(page, transition);
    }

    /**
     * Clears the collection of transitions stored in this parameter instance.
     */
    public void clearTransitions() {
        transitions.clear();
    }

    /**
     * @return an unmodifiable view of the transitions in this parameter.
     */
    public Map<Integer, PdfPageTransition> getTransitions() {
        return Collections.unmodifiableMap(transitions);
    }

    public PdfPageTransition getDefaultTransition() {
        return defaultTransition;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(transitions).append(defaultTransition)
                .append(fullScreen).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SetPagesTransitionParameters)) {
            return false;
        }
        SetPagesTransitionParameters parameter = (SetPagesTransitionParameters) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(transitions, parameter.getTransitions())
                .append(defaultTransition, parameter.getDefaultTransition())
                .append(fullScreen, parameter.isFullScreen()).isEquals();
    }
}