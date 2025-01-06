sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/m/MessageToast",
    "sap/ui/model/json/JSONModel"
], function (Controller, MessageToast, JSONModel) {
    "use strict";

    return Controller.extend("RouteApp.controller.Home", {
        
        onInit: function () {
            // Create a new JSON model
            var oModel = new JSONModel();
            // Load data from products.json (located in the same directory as the controller)
            oModel.loadData("products.json"); // Update path if the file is in a subfolder, e.g., "data/products.json"
            // Set the model to the view
            this.getView().setModel(oModel, "products");
        },

        onNavBack: function () {
            // Navigate back to the previous page
            history.go(-1);
        },

        onFilter: function (oEvent) {
            var sQuery = oEvent.getParameter("newValue");
            var oFilter = new sap.ui.model.Filter("Price", sap.ui.model.FilterOperator.LE, sQuery);
            var oBinding = this.byId("idProductsTable").getBinding("items");
            oBinding.filter(oFilter);
        },

        onReset: function () {
            var oTable = this.byId("idProductsTable");
            oTable.getBinding("items").filter([]);
        },

        onSort: function () {
            var oTable = this.byId("idProductsTable");
            var oBinding = oTable.getBinding("items");
            var aSorter = [new sap.ui.model.Sorter("Price", false)];
            oBinding.sort(aSorter);
        },

        onGroup: function () {
            var oTable = this.byId("idProductsTable");
            var oBinding = oTable.getBinding("items");
            var aGroupSorter = [new sap.ui.model.Sorter("SupplierName", false, true)];
            oBinding.sort(aGroupSorter);
        },

        onMenuAction: function (oEvent) {
            var oItem = oEvent.getParameter("item");
            var sText = oItem.getText();
            MessageToast.show("Selected: " + sText);
        },

        onBeforeMenuOpen: function (oEvent) {
        }
    });
});
