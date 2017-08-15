xui.Class('xui.UI.TreeGridPaginated', 'xui.UI.Div', {
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
							"id": "btnInsert",
							"hidden": false,
							"handler": false,
							"sub": [{
									"id": "insertRow",
									"caption": "",
									"label": "",
									"type": "button",
									"image": "https://gil.gg.go.kr/innoditor_u/image/toolbar/toolbar_btn_table_insert_row_down.gif",
									"tips": "Agregar"
								}
							],
							"caption": "btnInsert"
						}, {
							"id": "btnClone",
							"handler": false,
							"sub": [{
									"id": "cloneRow",
									"caption": "",
									"image": "http://cms.ewha.ac.kr/innoditor_u/image/toolbar/toolbar_btn_table_copy_row_down.gif",
									"tips": "Clonar"
								}
							],
							"caption": "btnClone"
						}, {
							"id": "btnDelete",
							"hidden": false,
							"handler": false,
							"sub": [{
									"id": "deleteRow",
									"image": "http://ks.chntdnc.com:88/icai/lt/images/deleterow.gif",
									"tips": "Eliminar",
									"caption": ""
								}
							],
							"caption": "btnDelete"
						}, {
							"id": "btnSave",
							"handler": false,
							"sub": [{
									"id": "saveGrid",
									"caption": "",
									"image": "http://ks.chntdnc.com:88/icai/lt/images/save.gif",
									"imageClass": "",
									"tips": "Guardar"
								}
							],
							"caption": "btnSave"
						}, {
							"id": "btnImport",
							"hidden": false,
							"handler": false,
							"sub": [{
									"id": "importFile",
									"caption": "",
									"image": "http://52.44.242.28/forestUPRA/img/buttons/csv.png",
									"tips": "Importar"
								}
							],
							"caption": "btnImport"
						}, {
							"id": "btnUpDown",
							"handler": false,
							"sub": [{
									"id": "upRow",
									"image": "http://52.44.242.28/forestUPRA/img/up.png",
									"caption": ""
								}, {
									"id": "downRow",
									"image": "http://52.44.242.28/forestUPRA/img/down.png",
									"caption": ""
								}
							],
							"caption": "btnUpDown"
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
				.setRowHandler(false)
				.setHeader([])
				.setRows([])
				);

			return children;
			// ]]Code created by CrossUI RAD Studio
		},
		_afterCellUpdated: function (profile, cell, options, isHotRow) {
			console.log("grid cambio ... ", arguments);
			if (cell._col.refColServices) {
				//invocar servicios dependientes ...
				for (var i = 0; i < cell._col.refColServices.length; ++i) {
					this.loadListServiceRow(cell._row.id, cell._col.refColServices[i]);
				}
			}
			//debugger;
		},
		loadListServiceRow: function (rowId, colId) {
			var h = this.grid.getHeader();
			var grid = this.grid;
			var r = rowId;
			var c = colId;
			for (var i0 = 0; i0 < h.length; ++i0) {
				if (h[i0].id == colId && h[i0].urlService) {
					var psArray = h[i0].parametersService;
					var urlService = h[i0].urlService;
					var query = "";

					for (var i = 0; i < psArray.length; ++i) {

						var value = null;
						if (query.length > 0) {
							query += '&';
						}

						for (var j = 0; j < h.length; ++j) {
							if (h[j].id == psArray[i].value) {
								value = this.grid.getCellbyRowCol(rowId, h[j].id).value;
								break;
							}
						}
						if (value == null) {
							grid.updateCellByRowCol(r, c, {
								editorListItems: [],
								value: ""
							}, undefined, true);
							return;
						}
						query += psArray[i].name + "=" + (value);
					}

					jQuery.getJSON(urlService, query)
					.done(function (items) {
						grid.updateCellByRowCol(r, c, {
							editorListItems: items,
							value: ""
						}, undefined, true);
					})
					.fail(
						function (e) {
						console.log(e);
						grid.updateCellByRowCol(r, c, {
							editorListItems: [],
							value: ""
						}, undefined, true);
					});

					break;
				}
			}
		},
		_onClickToolbar: function (profile, item, group, e, src) {
			var ns = this,
			uictrl = profile.boxing();
			switch (item.id) {
			case 'insertRow':
				this.insertBlankRow();
				break;
			case 'cloneRow':
				var grid = this.grid;
				grid.offEditor();
				var ar = grid.getActiveRow();
				if (ar == undefined) {
					xui.alert('No se encontr\u00f3 ningun elemento seleccionado');
					return;
				}
				var athis = this;
				xui.prompt(
					"Forest Forms", "Numero de copias de la fila seleccionada ...", "1",
					function (selValue) {
					console.log("selValue", selValue);
					var value = parseInt(selValue);
					if (isNaN(value)) {
						xui.alert('El valor ingresado no es valido');
						return;
					}
					if (value > 50) {
						xui.alert('Solo se puede clonar max 50 registros');
						return;
					}
					var prop = athis.get(0).properties;
					if (prop.maxRowsInserted && prop.maxRowsInserted > 0) {
						var r = athis.grid.getRows();
						var tot = 0;
						for (var i = 0; i < r.length; ++i)
							if (!(r[i].deleted === true))
								tot++;
						if (tot + value > prop.maxRowsInserted) {
							xui.alert('Regla', 'No se permite mas de '
								 + prop.maxRowsInserted + ' filas');
							return;
						}
					}
					athis.cloneRow(ar, value);
				});
				break;
			case 'deleteRow':
				var ar = this.grid.getActiveRow();
				this.deleteRow(ar);
				break;
			case 'saveGrid':
				break;
			case 'importFile':
				this.inputFile.click();
				break;
			case 'upRow':
				var ar = this.grid.getActiveRow();
				if (!ar) {
					return;
				}
				this.moveRow(ar, -1);
				break;
			case 'downRow':
				var ar = this.grid.getActiveRow();
				if (!ar) {
					return;
				}
				this.moveRow(ar, 1);
				break;
			}
		},
		insertBlankRow: function () {
			this.grid.offEditor();
			var prop = this.get(0).properties;
			if (prop.maxRowsInserted && prop.maxRowsInserted > 0) {
				var r = this.grid.getRows();
				var tot = 0;
				for (var i = 0; i < r.length; ++i) {
					if (!(r[i].deleted === true)) {
						tot++;
					}
				}
				if (tot >= prop.maxRowsInserted) {
					xui.alert('Regla', 'No se permite mas de '
						 + prop.maxRowsInserted + ' filas');
					return;
				}
			}
			var h = this.grid.getHeader();
			var rows = [];
			for (var i = 0; i < h.length; ++i) {
				var value = "";
				if (h[i].defaultValue) {
					value = h[i].defaultValue;
				}
				rows.push(value);
			}
			this.grid.insertRows([{
						isNew: true,
						cells: rows,
						rowClass: 'bgrows'
					}
				]);
		},
		cloneRow: function (ar, numCopias) {
			var h = this.grid.getHeader();
			for (var c = 0; c < numCopias; ++c) {
				var rows = [];
				for (var g = 0; g < ar.cells.length; ++g) {
					if (h[g].pk == true)
						rows.push({
							value: '',
							oValue: ''
						});
					else
						rows.push({
							value: ar.cells[g].value,
							oValue: ar.cells[g].value
						});
				}
				this.grid.insertRows([{
							isNew: true,
							cells: rows,
							rowClass: 'bgrows'
						}
					]);
			}
		},
		deleteRow: function (ar) {
			if (ar == undefined) {
				xui.alert('No se encontro ningun elemento seleccionado');
				return;
			}

			if (ar.deleted === true) {
				return;
			}
			var athis = this;
			xui.confirm("Forest Forms", "Eliminar la fila Seleccionada?", function () {
				if (ar.isNew === true) {
					athis.grid.removeRows([ar.id]);
				} else {
					ar.deleted = true;
					if (athis.get(0).properties.showRowsDeleted === true) {
						athis.grid.updateRow(ar.id, {
							rowClass: 'disactivatedRow',
							rowStyle: 'text-decoration:underline',
							disabled: true
						});
					} else {

						athis.grid.updateRow(ar.id, {
							rowClass: 'hiddenRow',
							rowStyle: 'text-decoration:underline',
							disabled: true
						});

					}
				}

			});

		},
		// Return array of string values, or NULL if CSV string not well formed.
		csvToArray: function (text) {
			var re_valid = /^\s*(?:'[^'\\]*(?:\\[\S\s][^'\\]*)*'|"[^"\\]*(?:\\[\S\s][^"\\]*)*"|[^,'"\s\\]*(?:\s+[^,'"\s\\]+)*)\s*(?:,\s*(?:'[^'\\]*(?:\\[\S\s][^'\\]*)*'|"[^"\\]*(?:\\[\S\s][^"\\]*)*"|[^,'"\s\\]*(?:\s+[^,'"\s\\]+)*)\s*)*$/;
			var re_value = /(?!\s*$)\s*(?:'([^'\\]*(?:\\[\S\s][^'\\]*)*)'|"([^"\\]*(?:\\[\S\s][^"\\]*)*)"|([^,'"\s\\]*(?:\s+[^,'"\s\\]+)*))\s*(?:,|$)/g;
			// Return NULL if input string is not well formed CSV string.
			if (!re_valid.test(text))
				return null;
			var a = []; // Initialize array to receive values.
			text.replace(re_value, // "Walk" the string using replace with callback.
				function (m0, m1, m2, m3) {
				// Remove backslash from \' in single quoted values.
				if (m1 !== undefined)
					a.push(m1.replace(/\\'/g, "'"));
				// Remove backslash from \" in double quoted values.
				else if (m2 !== undefined)
					a.push(m2.replace(/\\"/g, '"'));
				else if (m3 !== undefined)
					a.push(m3);
				return ''; // Return empty string.
			});
			// Handle special case of empty last value.
			if (/,\s*$/.test(text))
				a.push('');
			return a;
		},
		parseCSV: function (text) {
			var lines = text.split("\n");
			//var headers = this.csvToArray(lines[0]);
			var result = [];
			for (var i = 0; i < lines.length; ++i) {
				if (!lines[i]) {
					continue;
				}
				var columns = this.csvToArray(lines[i]);
				result.push({
					isNew: true,
					cells: columns
				});
			}
			return result;

		},
		importFile: function () {
			console.log("file sel ...");
			var file = this.inputFile.files[0];
			var filename = file.name.toLowerCase();
			var tmppath = window.URL.createObjectURL(file);
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.overrideMimeType('text/csv');
			xmlhttp.open("GET", tmppath, false);
			xmlhttp.send();
			var text = xmlhttp.responseText;
			var data = this.parseCSV(text);
			//TODO aplicar conversiones leyendo el header
			this.grid.setRows(data);
		},
		getHeader: function () {
			return this.grid.getHeader();
		},
		moveRow: function (ar, dir) {
			var rows = this.grid.getRows();
			var index = rows.indexOf(ar);
			console.log(index, " rows ", rows);
			if (index < 0) {
				console.log("No index row element");
				return;
			}
			if (dir == -1 && index == 0) {
				return;
			}
			if (dir > 0 && index == rows.length - 1) {
				return;
			}
			var old = rows[index];
			rows[index] = rows[index + dir];
			rows[index + dir] = old;
			this.grid.setRows(rows);
			this.grid.refresh();
			var nindex = index + dir;
			setTimeout(function () {
				this.grid.setActiveRow(nindex);
			}
				.bind(this), 250);
		},
		getFormattedDate: function (date) {
			var year = date.getFullYear();

			var month = (1 + date.getMonth()).toString();
			month = month.length > 1 ? month : '0' + month;

			var day = date.getDate().toString();
			day = day.length > 1 ? day : '0' + day;

			return year + '-' + month + '-' + day;
		},
		/**
		 * read json object represents all values off grid
		 */
		getJSONValue: function (validate) {
			var retVal = {
				fields: [],
				features: []
			};
			var h = this.grid.getHeader();
			for (var i = 0; i < h.length; ++i) {
				retVal.fields.push({
					name: h[i].id,
					type: h[i].type,
					required: !!h[i].required
				});
			}
			var rows = this.grid.getRows();
			for (var j = 0; j < rows.length; ++j) {
				var cells = rows[j].cells;
				var newRow = {
					attributes: {}
				};
				for (var i = 0; i < h.length; ++i) {
					var value = cells[i].value;
					if (value == null) {
						value = "";
					}
					if (h[i].type == 'date' && value) {
						value = this.getFormattedDate(value);
						console.log("date " + value);
					}
					newRow.state = rows[j].isNew ? "new" : r[i].deleted ? "delete" : "edit";
					newRow.attributes[h[i].id] = value;
				}
				retVal.features.push(newRow);
			}
			return JSON.stringify(retVal);
		}
	},
	Static: {
		DataModel: {
			rowNumbered: {
				ini: true,
				set: function (rowNumbered) {
					this.boxing().grid.setRowNumbered(rowNumbered);
					this.properties.rowHandler = rowNumbered;
				}
			},
			rowHandler: {
				ini: true,
				set: function (rowHandler) {
					this.boxing().grid.setRowHandler(rowHandler);
					this.properties.rowHandler = rowHandler;
				}
			},

			headerHeight: {
				ini: '2 em',
				set: function (headerHeight) {
					this.boxing().grid.setHeaderHeight(headerHeight);
					this.properties.headerHeight = headerHeight;
				}
			},
			header: {
				ini: [],
				set: function (header) {
					this.boxing().grid.setHeader(header);
					this.properties.header = header;
				}
			},
			btnInsert: {
				ini: false,
				action: function (value) {
					this.boxing().toolbar.showGroup("btnInsert", value);
					this.boxing().div.refresh();
				}
			},
			btnClone: {
				ini: false,
				action: function (value) {
					this.boxing().toolbar.showGroup("btnClone", value);
				}
			},
			btnDelete: {
				ini: false,
				action: function (value) {
					this.boxing().toolbar.showGroup("btnDelete", value);
				}
			},
			btnSave: {
				ini: false,
				action: function (value) {
					this.boxing().toolbar.showGroup("btnSave", value);
				}
			},
			btnImport: {
				ini: false,
				action: function (value) {
					this.boxing().toolbar.showGroup("btnImport", value);
				}
			},
			btnUpDown: {
				ini: false,
				action: function (value) {
					this.boxing().toolbar.showGroup("btnUpDown", value);
				}
			},
			urlService: '',
			parametersService: {
				ini: [],
				caption: "parametersService",
				trigger: function () {
					if (this.getProperties().parametersService && typeof(this.getProperties().parametersService) == 'string') {
						// compatibilidad hacia atras cuando era un string
						var parts = this.getProperties().parametersService.replace(/\s/g, '').split(',');
						var result = [];
						for (var i = 0; i < parts.length; ++i) {
							result.push({
								name: parts[i],
								value: parts[i]
							});
						}
						this.setParametersService(result);
					}
					CONF.editParameters(this, this.getProperties().parametersService);
				}
			},
			maxRowsInserted: 0,
			showRowsDeleted: false,
			paginate: {
				ini: false,
				action: function (value) {
					this.boxing().divPag.setDisplay(value ? '' : 'none');
					this.boxing().div.refresh();
				}
			}
		},
		RenderTrigger: function () {
			var instance = this.boxing();
			instance.refW = window;
			instance.append(instance.iniComponents());
			var buttons = ['btnInsert', 'btnClone', 'btnDelete', 'btnSave', 'btnImport', 'btnUpDown'];
			for (var i = 0; i < buttons.length; ++i) {
				instance.toolbar.showGroup(buttons[i], this.properties[buttons[i]] === true);
			}
			instance.grid.setHeader(this.properties.header ? this.properties.header : []);
			instance.grid.setHeaderHeight(this.properties.headerHeight ? this.properties.headerHeight : '2 em');
			instance.grid.setRowHandler(this.properties.rowHandler ? this.properties.rowHandler : true);
			instance.grid.setRowNumbered(this.properties.rowNumbered ? this.properties.rowNumbered : true);
			instance.divPag.setDisplay(this.properties.paginate === true ? '' : 'none');
			if (this.$inDesign) {}
			else {
				instance.grid.afterCellUpdated("_afterCellUpdated");
				var input = document.createElement('input');
				input.setAttribute("type", "file");
				input.setAttribute("style", "display:none");
				input.setAttribute("accept", ".csv");
				instance.inputFile = input;
				input.onchange = function () {
					instance.importFile();
				};
				document.body.appendChild(input);
			}

		}
	}

});
