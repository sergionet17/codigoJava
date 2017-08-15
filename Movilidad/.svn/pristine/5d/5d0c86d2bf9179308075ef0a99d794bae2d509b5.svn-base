xui.Class('xui.UI.UploadMultipleGrid', 'xui.UI.Div', {
	Instance: {
		setRows: function (rows) {
			this.grid.setRows(rows);
		},
		iniComponents: function () {
			// [[Code created by CrossUI RAD Studio
			var host = this,
			children = [],
			append = function (child) {
				children.push(child.get(0));
			};

			append(
				xui.create("xui.UI.Div")
				.setHost(host, "div")
				.setDock("fill")
				.setLeft("3.3333333333333335em")
				.setTop("5.833333333333333em")
				.setWidth("50em")
				.setHeight("30em"));

			host.div.append(
				xui.create("xui.UI.Div")
				.setHost(host, "divPag")
				.setDock("bottom")
				.setLeft("20em")
				.setTop("22.5em")
				.setHeight("3.3333333333333335em")
				.setCustomStyle({
					"KEY": {
						"background-color": "#D3D3D3"
					}
				}));

			host.div.append(
				xui.create("xui.UI.ToolBar")
				.setHost(host, "toolbar")
				.setItems([{
							"id": "btnSave",
							"handler": false,
							"sub": [{
									"id": "saveGrid",
									"caption": "Guardar",
									"imageClass": "xui-uicmd-save",
									"tips": "Guardar"
								}
							],
							"caption": "btnSave"
						}, {
							"id": "btnInsert",
							"hidden": false,
							"handler": false,
							"sub": [{
									"id": "insertRow",
									"caption": "Agregar",
									"label": "",
									"type": "button",
									"imageClass": "xui-uicmd-add",
									"tips": "Agregar"
								}
							],
							"caption": "btnInsert"
						}, {
							"id": "btnDelete",
							"hidden": false,
							"handler": false,
							"sub": [{
									"id": "deleteRow",
									"caption": "Eliminar",
									"imageClass": "xui-uicmd-delete",
									"tips": "Eliminar"
								}
							],
							"caption": "btnDelete"
						}
					])
				.setTop("11.666666666666666em")
				.onClick("_onClickToolbar"));

			host.div.append(
				xui.create("xui.UI.TreeGrid")
				.setHost(host, "grid")
				.setAltRowsBg(true)
				.setLeft("0em")
				.setTop("0em")
				.setEditable(true)
				.setRowHandler(true)
				.setHeader([{
							id: 'fileName',
							caption: 'Archivo',
							editable: false
						}, {
							id: 'percent',
							caption: 'Progreso',
							type: 'progress',
							width: "80px",
							editable: false,
							"readonly": true,
							"editMode": "inline"
						}, {
							id: "status",
							caption: 'Estado',
							editable: false
						},
						 {
							id: "reference",
							caption: 'referencia',
							editable: false,
							hidden:true
						}
					])
				.setRows([]));

			return children;
			// ]]Code created by CrossUI RAD Studio
		},
		_onClickToolbar: function (profile, item, group, e, src) {
			var ns = this,
			uictrl = profile.boxing();
			switch (item.id) {
			case 'insertRow':
				var input = document.createElement('input');
				input.setAttribute("type", "file");
				input.setAttribute("multiple", "true");
				input.setAttribute("style", "display:none");
				input.setAttribute("accept", ".csv,.doc,.docx,.xlsx,.xls,.html,.gif,.png,.jpg");
				this.inputFile = input;
				var instance = this;
				input.onchange = function () {
					instance.addFile();
				};
				document.body.appendChild(input);
				this.getReferences().push(input);
				this.inputFile.click();
				break;
			case 'saveGrid':

				var rows = this.grid.getRows();
				var athis = this;
				window.total_files_send = 0;
				for (var i = 0; i < rows.length; ++i) {
					if (rows[i].isNew) {
						window.total_files_send++;
					}
				}
				if (window.total_files_send > 0) {
					athis.div.busy();
				}
				var unlocker = function () {
					window.total_files_send--;
					if (window.total_files_send <= 0) {
						athis.div.free();
						var rows = athis.grid.getRows();
						var removeInputs = true;
						var r = athis.getReferences();
						for (var i = 0; i < rows.length; ++i) {
							if (rows[i].isNew) {
								removeInputs = false;
							}
							
						}
					}
				};
				for (var i = 0; i < rows.length; ++i) {
					if (!rows[i].isNew) {
						continue;
					}
					var uploaderForm = new FormData(); // Create new FormData
					var id = rows[i].id;
					var size = rows[i].reference.size;
					uploaderForm.append("action", "upload"); // append extra parameters if you wish.
					uploaderForm.append("fileToUpload", rows[i].reference); // append the next file for upload

					var xhr = new XMLHttpRequest();
					(function (xhr, id, athis, length) {
						var transferComplete = function (result) {
							unlocker();
							var response = JSON.parse(result.target.responseText);
							if (response.success) {
								athis.grid.updateRow(id, {
									isNew: false
								});
								athis.grid.updateCellByRowCol(id, "status", "completo")
								athis.grid.updateCellByRowCol(id, "percent", 100);
								athis.grid.updateCellByRowCol(id, "reference", response.reference);
							} else {
								athis.grid.updateCellByRowCol(id, "status", response.message);
								athis.grid.updateCellByRowCol(id, "percent", 0);
							}
						};
						var transferFailed = function () {
							unlocker();
							athis.grid.updateCellByRowCol(id, "status", "error");
							athis.grid.updateCellByRowCol(id, "percent", 0);
						};
						var transferCanceled = function () {
							unlocker();
							athis.grid.updateCellByRowCol(id, "status", "cancelada");
							athis.grid.updateCellByRowCol(id, "percent", 0);
						};
						xhr.addEventListener("load", transferComplete, false);
						xhr.addEventListener("error", transferFailed, false);
						xhr.addEventListener("abort", transferCanceled, false);
						xhr.addEventListener("progress", function (e) {
							var total = e.total == 0 ? length : e.total;
							var loaded = e.loaded;
							var percent = (100 / total) * loaded; // Calculate percentage of loaded data
							// I animate progress object. Notice that i use "this.progressId" which i created on loadstart event.
							console.log(id, percent, total, loaded);
							athis.grid.updateCellByRowCol(id, "percent", percent);

						});
					})(xhr, id, athis, size);
					xhr.open("POST", athis.getUrlService());
					xhr.send(uploaderForm);
				}

				break;
			case 'deleteRow':
				var ar = this.grid.getActiveRow();
				if (ar && ar.isNew === true) {
					this.grid.removeRows([ar.id]);
				}
				break;
			}
		},
		getSelectedReference: function(){
			var ar = this.grid.getActiveRow();
				if (!ar || ar.isNew === true) {
					return null;
				}
				return this.grid.getCellbyRowCol(ar.id,"reference").value;
		},
		addFile: function () {
			for (var i = 0; i < this.inputFile.files.length; ++i) {
				var file = this.inputFile.files[i];
				this.grid.insertRows([{
							isNew: true,
							reference: file,
							cells: [file.name, 0, '',''],
							rowClass: 'bgrows'
						}
					]);
			}

		},
		deleteRow: function (ar) {},
		getHeader: function () {
			return this.grid.getHeader();
		}
	},
	Static: {
		DataModel: {
			selectedReference:{
				hidden: true,
				ini:''
			},
			references: {
				hidden: true,
				ini: []
			},
			header: {
				ini: [],
				set: function (header) {
					this.boxing().grid.setHeader(header);
					this.properties.header = header;
				}
			},
			urlService: '',
			paginate: {
				ini: false,
				action: function (value) {
					this.boxing().divPag.setDisplay(value ? '' : 'none');
					this.boxing().div.refresh();
				}
			}
		},
		 EventHandlers:{
            afterRowActive:function(profile, row){
				
			}
        },
		RenderTrigger: function () {
			var instance = this.boxing();
			instance.refW = window;
			instance.append(instance.iniComponents());
			instance.divPag.setDisplay(this.properties.paginate === true ? '' : 'none');
			if (this.$inDesign) {}
			else {
				instance.grid.setEvents(instance.getEvents());
			}

		}
	}

});
