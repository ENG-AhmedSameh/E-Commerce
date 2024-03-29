package com.example.ecommerce.presentation.controller.util;

public class ViewResolver {

    private String view;
    private ResolveAction resolveAction;

    public ViewResolver() {
    }

    public ViewResolver(final String view) {
        this.view = view;
        resolveAction = ResolveAction.FORWARD;
    }

    public String getView() {
        return view;
    }

    public void setView(final String view) {
        this.view = view;
    }

    public ResolveAction getResolveAction() {
        return resolveAction;
    }

    public void setResolveAction(final ResolveAction resolveAction) {
        this.resolveAction = resolveAction;
    }

    public void forward(final String view) {
        setView(view);
        resolveAction = ResolveAction.FORWARD;
    }

    public void redirect(final String view) {
        setView(view);
        resolveAction = ResolveAction.REDIRECT;
    }
    public void sendOnlyResponse() {
        resolveAction = ResolveAction.ONLY_RESPONSE;
    }
}
