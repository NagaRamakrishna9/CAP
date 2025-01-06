sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent"
], function (Controller, UIComponent) {
    "use strict";

    return Controller.extend("RouteApp.controller.App", {
        onInit: function () {
        },
      
        // onLoginpress: function() {
        //     var username = this.byId("userInput").getValue();
        //     var password = this.byId("passwordInput").getValue();
        
        //     if (username === "Inflexion" && password === "12345") {
        //         var oRouter = this.getOwnerComponent().getRouter();
        //         oRouter.navTo("home");
        //     }
        //     },
            onRegisterpress: function(){

            },

        getRouter: function () {
            return UIComponent.getRouterFor(this);
        }
    });
});
