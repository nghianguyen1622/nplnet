"use strict";

!function (npl, $) {
  "use strict";

  npl.Package.name = "NplNet";
  npl.Package.version = "3.2";
  var $win = $(window),
    $body = $('body'),
    $doc = $(document),
    //class names
    _body_theme = 'nio-theme',
    _menu = 'nk-menu',
    _mobile_nav = 'mobile-menu',
    _header = 'nk-header',
    _header_menu = 'nk-header-menu',
    _sidebar = 'nk-sidebar',
    _sidebar_mob = 'nk-sidebar-mobile',
    //breakpoints
    _break = npl.Break;
  function extend(obj, ext) {
    Object.keys(ext).forEach(function (key) {
      obj[key] = ext[key];
    });
    return obj;
  }
  // ClassInit @v1.0
  npl.ClassBody = function () {
    npl.AddInBody(_sidebar);
  };

  // ClassInit @v1.0
  npl.ClassNavMenu = function () {
    npl.BreakClass('.' + _header_menu, _break.lg, {
      timeOut: 0
    });
    npl.BreakClass('.' + _sidebar, _break.lg, {
      timeOut: 0,
      classAdd: _sidebar_mob
    });
    $win.on('resize', function () {
      npl.BreakClass('.' + _header_menu, _break.lg);
      npl.BreakClass('.' + _sidebar, _break.lg, {
        classAdd: _sidebar_mob
      });
    });
  };

  // Code Prettify @v1.0
  npl.Prettify = function () {
    window.prettyPrint && prettyPrint();
  };

  // Copied @v1.0
  npl.Copied = function () {
    var clip = '.clipboard-init',
      target = '.clipboard-text',
      sclass = 'clipboard-success',
      eclass = 'clipboard-error';

    // Feedback
    function feedback(el, state) {
      var $elm = $(el),
        $elp = $elm.parent(),
        copy = {
          text: 'Copy',
          done: 'Copied',
          fail: 'Failed'
        },
        data = {
          text: $elm.data('clip-text'),
          done: $elm.data('clip-success'),
          fail: $elm.data('clip-error')
        };
      copy.text = data.text ? data.text : copy.text;
      copy.done = data.done ? data.done : copy.done;
      copy.fail = data.fail ? data.fail : copy.fail;
      var copytext = state === 'success' ? copy.done : copy.fail,
        addclass = state === 'success' ? sclass : eclass;
      $elp.addClass(addclass).find(target).html(copytext);
      setTimeout(function () {
        $elp.removeClass(sclass + ' ' + eclass).find(target).html(copy.text).blur();
        $elp.find('input').blur();
      }, 2000);
    }

    // Init ClipboardJS
    if (ClipboardJS.isSupported()) {
      var clipboard = new ClipboardJS(clip);
      clipboard.on('success', function (e) {
        feedback(e.trigger, 'success');
        e.clearSelection();
      }).on('error', function (e) {
        feedback(e.trigger, 'error');
      });
    } else {
      $(clip).css('display', 'none');
    }
    ;
  };

  // CurrentLink Detect @v1.0
  npl.CurrentLink = function () {
    var _link = '.nk-menu-link, .menu-link, .nav-link',
      _currentURL = window.location.href,
      fileName = _currentURL.substring(0, _currentURL.indexOf("#") == -1 ? _currentURL.length : _currentURL.indexOf("#")),
      fileName = fileName.substring(0, fileName.indexOf("?") == -1 ? fileName.length : fileName.indexOf("?"));
    $(_link).each(function () {
      var self = $(this),
        _self_link = self.attr('href');
      if (fileName.match(_self_link)) {
        self.closest("li").addClass('active current-page').parents().closest("li").addClass("active current-page");
        self.closest("li").children('.nk-menu-sub').css('display', 'block');
        self.parents().closest("li").children('.nk-menu-sub').css('display', 'block');
        this.scrollIntoView({
          block: "start"
        });
      } else {
        self.closest("li").removeClass('active current-page').parents().closest("li:not(.current-page)").removeClass("active");
      }
    });
  };

  // PasswordSwitch @v1.0
  npl.PassSwitch = function () {
    npl.Passcode('.passcode-switch');
  };

  // Toastr Message @v1.0 
  npl.Toast = function (msg, ttype, opt) {
    var ttype = ttype ? ttype : 'info',
      msi = '',
      ticon = ttype === 'info' ? 'ni ni-info-fill' : ttype === 'success' ? 'ni ni-check-circle-fill' : ttype === 'error' ? 'ni ni-cross-circle-fill' : ttype === 'warning' ? 'ni ni-alert-fill' : '',
      def = {
        position: 'bottom-right',
        ui: '',
        icon: 'auto',
        clear: false
      },
      attr = opt ? extend(def, opt) : def;
    attr.position = attr.position ? 'toast-' + attr.position : 'toast-bottom-right';
    attr.icon = attr.icon === 'auto' ? ticon : attr.icon ? attr.icon : '';
    attr.ui = attr.ui ? ' ' + attr.ui : '';
    msi = attr.icon !== '' ? '<span class="toastr-icon"><em class="icon ' + attr.icon + '"></em></span>' : '', msg = msg !== '' ? msi + '<div class="toastr-text">' + msg + '</div>' : '';
    if (msg !== "") {
      if (attr.clear === true) {
        toastr.clear();
      }
      var option = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": attr.position + attr.ui,
        "closeHtml": '<span class="btn-trigger">Close</span>',
        "preventDuplicates": true,
        "showDuration": "1500",
        "hideDuration": "1500",
        "timeOut": "2000",
        "toastClass": "toastr",
        "extendedTimeOut": "3000"
      };
      toastr.options = extend(option, attr);
      toastr[ttype](msg);
    }
  };

  // Toggle Screen @v1.0
  npl.TGL.screen = function (elm) {
    if ($(elm).exists()) {
      $(elm).each(function () {
        var ssize = $(this).data('toggle-screen');
        if (ssize) {
          $(this).addClass('toggle-screen-' + ssize);
        }
      });
    }
  };

  // Toggle Content @v1.0
  npl.TGL.content = function (elm, opt) {
    var toggle = elm ? elm : '.toggle',
      $toggle = $(toggle),
      $contentD = $('[data-content]'),
      toggleBreak = true,
      toggleCurrent = false,
      def = {
        active: 'active',
        content: 'content-active',
        "break": toggleBreak
      },
      attr = opt ? extend(def, opt) : def;
    npl.TGL.screen($contentD);
    $toggle.on('click', function (e) {
      toggleCurrent = this;
      npl.Toggle.trigger($(this).data('target'), attr);
      e.preventDefault();
    });
    $doc.on('mouseup', function (e) {
      if (toggleCurrent) {
        var $toggleCurrent = $(toggleCurrent),
          currentTarget = $(toggleCurrent).data('target'),
          $contentCurrent = $("[data-content=\"".concat(currentTarget, "\"]")),
          $dpd = $('.datepicker-dropdown'),
          $tpc = $('.ui-timepicker-container'),
          $mdl = $('.modal');
        if (!$toggleCurrent.is(e.target) && $toggleCurrent.has(e.target).length === 0 && !$contentCurrent.is(e.target) && $contentCurrent.has(e.target).length === 0 && $(e.target).closest('.select2-container').length === 0 && !$dpd.is(e.target) && $dpd.has(e.target).length === 0 && !$tpc.is(e.target) && $tpc.has(e.target).length === 0 && !$mdl.is(e.target) && $mdl.has(e.target).length === 0) {
          npl.Toggle.removed($toggleCurrent.data('target'), attr);
          toggleCurrent = false;
        }
      }
    });
    $win.on('resize', function () {
      $contentD.each(function () {
        var content = $(this).data('content'),
          ssize = $(this).data('toggle-screen'),
          toggleBreak = _break[ssize];
        if (npl.Win.width > toggleBreak) {
          npl.Toggle.removed(content, attr);
        }
      });
    });
  };

  // ToggleExpand @v1.0
  npl.TGL.expand = function (elm, opt) {
    var toggle = elm ? elm : '.expand',
      def = {
        toggle: true
      },
      attr = opt ? extend(def, opt) : def;
    $(toggle).on('click', function (e) {
      npl.Toggle.trigger($(this).data('target'), attr);
      e.preventDefault();
    });
  };

  // Dropdown Menu @v1.0
  npl.TGL.ddmenu = function (elm, opt) {
    var imenu = elm ? elm : '.nk-menu-toggle',
      def = {
        active: 'active',
        self: 'nk-menu-toggle',
        child: 'nk-menu-sub'
      },
      attr = opt ? extend(def, opt) : def;
    $(imenu).on('click', function (e) {
      if (npl.Win.width < _break.lg || $(this).parents().hasClass(_sidebar)) {
        npl.Toggle.dropMenu($(this), attr);
      }
      e.preventDefault();
    });
  };

  // Show Menu @v1.0
  npl.TGL.showmenu = function (elm, opt) {
    var toggle = elm ? elm : '.nk-nav-toggle',
      $toggle = $(toggle),
      $contentD = $('[data-content]'),
      toggleBreak = $contentD.hasClass(_header_menu) ? _break.lg : _break.xl,
      toggleOlay = _sidebar + '-overlay',
      toggleClose = {
        profile: true,
        menu: false
      },
      def = {
        active: 'toggle-active',
        content: _sidebar + '-active',
        body: 'nav-shown',
        overlay: toggleOlay,
        "break": toggleBreak,
        close: toggleClose
      },
      attr = opt ? extend(def, opt) : def;
    $toggle.on('click', function (e) {
      npl.Toggle.trigger($(this).data('target'), attr);
      e.preventDefault();
    });
    $doc.on('mouseup', function (e) {
      if (!$toggle.is(e.target) && $toggle.has(e.target).length === 0 && !$contentD.is(e.target) && $contentD.has(e.target).length === 0 && npl.Win.width < toggleBreak) {
        npl.Toggle.removed($toggle.data('target'), attr);
      }
    });
    $win.on('resize', function () {
      if ((npl.Win.width < _break.xl || npl.Win.width < toggleBreak) && !npl.State.isMobile) {
        npl.Toggle.removed($toggle.data('target'), attr);
      }
    });
  };

  // Compact Sidebar @v1.0
  npl.sbCompact = function () {
    var toggle = '.nk-nav-compact',
      $toggle = $(toggle),
      $content = $('[data-content]'),
      $sidebar = $('.' + _sidebar),
      $sidebar_body = $('.' + _sidebar + '-body');
    var isCompact = sessionStorage.getItem('sidebarState') === 'compact';
    if (isCompact) {
      $toggle.addClass('compact-active');
      $content.addClass('is-compact');
    }
    $toggle.on('click', function (e) {
      e.preventDefault();
      var $self = $(this),
        get_target = $self.data('target'),
        $self_content = $('[data-content=' + get_target + ']');
      $self.toggleClass('compact-active');
      $self_content.toggleClass('is-compact');
      sessionStorage.setItem('sidebarState', $self_content.hasClass('is-compact') ? 'compact' : '');
    });
    $sidebar_body.on('mouseenter', function (e) {
      if ($sidebar.hasClass('is-compact')) {
        $sidebar.addClass('has-hover');
      }
    });
    $sidebar_body.on('mouseleave', function (e) {
      if ($sidebar.hasClass('is-compact')) {
        $sidebar.removeClass('has-hover');
      }
    });
  };

  // Animate FormSearch @v1.0
  npl.Ani.formSearch = function (elm, opt) {
    var def = {
        active: 'active',
        timeout: 400,
        target: '[data-search]'
      },
      attr = opt ? extend(def, opt) : def;
    var $elem = $(elm),
      $target = $(attr.target);
    if ($elem.exists()) {
      $elem.on('click', function (e) {
        e.preventDefault();
        var $self = $(this),
          the_target = $self.data('target'),
          $self_st = $('[data-search=' + the_target + ']'),
          $self_tg = $('[data-target=' + the_target + ']');
        if (!$self_st.hasClass(attr.active)) {
          $self_tg.add($self_st).addClass(attr.active);
          $self_st.find('input').focus();
        } else {
          $self_tg.add($self_st).removeClass(attr.active);
          setTimeout(function () {
            $self_st.find('input').val('');
          }, attr.timeout);
        }
      });
      $doc.on({
        keyup: function keyup(e) {
          if (e.key === "Escape") {
            $elem.add($target).removeClass(attr.active);
          }
        },
        mouseup: function mouseup(e) {
          if (!$target.find('input').val() && !$target.is(e.target) && $target.has(e.target).length === 0 && !$elem.is(e.target) && $elem.has(e.target).length === 0) {
            $elem.add($target).removeClass(attr.active);
          }
        }
      });
    }
  };

  // Animate FormElement @v1.0
  npl.Ani.formElm = function (elm, opt) {
    var def = {
        focus: 'focused'
      },
      attr = opt ? extend(def, opt) : def;
    if ($(elm).exists()) {
      $(elm).each(function () {
        var $self = $(this);
        if ($self.val()) {
          $self.parent().addClass(attr.focus);
        }
        $self.on({
          focus: function focus() {
            $self.parent().addClass(attr.focus);
          },
          blur: function blur() {
            if (!$self.val()) {
              $self.parent().removeClass(attr.focus);
            }
          }
        });
      });
    }
  };

  // Form Validate @v1.0
  npl.Validate = function (elm, opt) {
    if ($(elm).exists()) {
      $(elm).each(function () {
        var def = {
            errorElement: "span"
          },
          attr = opt ? extend(def, opt) : def;
        $(this).validate(attr);
        npl.Validate.OnChange('.js-select2');
        npl.Validate.OnChange('.date-picker');
        npl.Validate.OnChange('.js-tagify');
      });
    }
  };

  //On change validation for third party plugins
  npl.Validate.OnChange = function (elm) {
    $(elm).on('change', function () {
      $(this).valid();
    });
  };
  npl.Validate.init = function () {
    npl.Validate('.form-validate', {
      errorElement: "span",
      errorClass: "invalid",
      errorPlacement: function errorPlacement(error, element) {
        if (element.parents().hasClass('input-group')) {
          error.appendTo(element.parent().parent());
        } else {
          error.appendTo(element.parent());
        }
      }
    });
  };

  // Dropzone @v1.1
  npl.Dropzone = function (elm, opt) {
    if ($(elm).exists()) {
      $(elm).each(function () {
        var maxFiles = $(elm).data('max-files'),
          maxFiles = maxFiles ? maxFiles : null;
        var maxFileSize = $(elm).data('max-file-size'),
          maxFileSize = maxFileSize ? maxFileSize : 256;
        var acceptedFiles = $(elm).data('accepted-files'),
          acceptedFiles = acceptedFiles ? acceptedFiles : null;
        var def = {
            autoDiscover: false,
            maxFiles: maxFiles,
            maxFilesize: maxFileSize,
            acceptedFiles: acceptedFiles
          },
          attr = opt ? extend(def, opt) : def;
        $(this).addClass('dropzone').dropzone(attr);
      });
    }
  };

  // Dropzone Init @v1.0
  npl.Dropzone.init = function () {
    npl.Dropzone('.upload-zone', {
      url: "/images"
    });
  };

  // Wizard @v1.0
  npl.Wizard = function () {
    var $wizard = $(".nk-wizard");
    if ($wizard.exists()) {
      $wizard.each(function () {
        var $self = $(this),
          _self_id = $self.attr('id'),
          $self_id = $('#' + _self_id).show();
        $self_id.steps({
          headerTag: ".nk-wizard-head",
          bodyTag: ".nk-wizard-content",
          labels: {
            finish: "Submit",
            next: "Next",
            previous: "Prev",
            loading: "Loading ..."
          },
          titleTemplate: '<span class="number">0#index#</span> #title#',
          onStepChanging: function onStepChanging(event, currentIndex, newIndex) {
            // Allways allow previous action even if the current form is not valid!
            if (currentIndex > newIndex) {
              return true;
            }
            // Needed in some cases if the user went back (clean up)
            if (currentIndex < newIndex) {
              // To remove error styles
              $self_id.find(".body:eq(" + newIndex + ") label.error").remove();
              $self_id.find(".body:eq(" + newIndex + ") .error").removeClass("error");
            }
            $self_id.validate().settings.ignore = ":disabled,:hidden";
            return $self_id.valid();
          },
          onFinishing: function onFinishing(event, currentIndex) {
            $self_id.validate().settings.ignore = ":disabled";
            return $self_id.valid();
          },
          onFinished: function onFinished(event, currentIndex) {
            window.location.href = "#";
          }
        }).validate({
          errorElement: "span",
          errorClass: "invalid",
          errorPlacement: function errorPlacement(error, element) {
            error.appendTo(element.parent());
          }
        });
      });
    }
  };

  // DataTable @1.1
  npl.gird = function (elm, opt, externalOptions) {
    if ($(elm).exists()) {
      $(elm).each(function () {
        var auto_responsive = $(this).data('auto-responsive'),
          has_export = typeof opt.buttons !== 'undefined' && opt.buttons ? true : false;
        var export_title = $(this).data('export-title') ? 'Xuất File' : $(this).data('export-title');
        var btn = has_export ? '<"dt-export-buttons d-flex align-center"<"dt-export-title d-none d-md-inline-block">B>' : '',
          btn_cls = has_export ? ' with-export' : '';
        var dom_normal = '<"row justify-between g-2' + btn_cls + '"<"col-7 col-sm-4 text-start"f><"col-5 col-sm-8 text-end"<"datatable-filter"<"d-flex justify-content-end g-2"' + btn + 'l>>>><"datatable-wrap my-3"t><"row align-items-center"<"col-7 col-sm-12 col-md-9"p><"col-5 col-sm-12 col-md-3 text-start text-md-end"i>>';
        var dom_separate = '<"row justify-between g-2' + btn_cls + '"<"col-7 col-sm-4 text-start"f><"col-5 col-sm-8 text-end"<"datatable-filter"<"d-flex justify-content-end g-2"' + btn + 'l>>>><"my-3"t><"row align-items-center"<"col-7 col-sm-12 col-md-9"p><"col-5 col-sm-12 col-md-3 text-start text-md-end"i>>';
        var dom = $(this).hasClass('is-separate') ? dom_separate : dom_normal;
        var def = {
            responsive: true,
            autoWidth: false,
            dom: dom,
            language: {
              loadingRecords: '<div class="spinner-border text-warning m-5" role="status"></div>',
              search: "",
              searchPlaceholder: "Tìm kiếm",
              lengthMenu: "<span class='d-none d-sm-inline-block'>Hiển thị</span><div class='form-control-select'> _MENU_ </div>",
              info: "_START_ - _END_ / _TOTAL_",
              infoEmpty: "0",
              infoFiltered: "( Tổng _MAX_  )",
              paginate: {
                "first": "Trang đầu",
                "last": "Trang cuối",
                "next": "Tới",
                "previous": "Lui"
              }
              
            },
            scrollX: true,
            scrollY: true,
            scrollCollapse: true,
            
          },
          attr = externalOptions ? extend(extend(def, opt), externalOptions) : extend(def, opt);
        attr = auto_responsive === false ? extend(attr, {
          responsive: false
        }) : attr;
        $(this).DataTable(attr);
        $('.dt-export-title').text(export_title);
      });
    }
  };

	// DataTable Init @v1.0
	npl.gird.init = function() {
		npl.gird('.datatable-init', {
			responsive: {
				details: true
			},
		});
	};
  
	// Draw Table with API 
	npl.gird.create = function (elm, arrURL, conditon, arr,  opt) {
		var myExternalOptions = {
		ajax: {
			url: arrURL.api,
			dataSrc: "",
			data: conditon
		},
		columns: arr.map(function (columnInfo) {
				return {
					"data": columnInfo.field,
					"title": '<span class="sub-text">' + columnInfo.title + '</span>',
					
					"render": function(data, type, row, meta) {
						if (columnInfo.field != null) {
							if(columnInfo.img){
								if (data === null || data === undefined) {
									return "";
								} else {
									return `<img height="28px" src="${contextPath+ data}" alt="" class="thumb">`;
								}
							}if(columnInfo.status){
								if(data=='Y'){
									return `<a class="btn btn-icon" style="color: #3ed900" >
												<em class="icon ni ni-unlock"></em></a>`;
								}else{
									return `<a class="btn btn-icon" style="color: #3ed900" >
												<em class="icon ni ni-lock-alt"></em></a>`;
								}
							}if(columnInfo.list){
								var arr = data.split(',');
								let txt =`<span class="ec-sub-cat-list">
											<span class="badge badge-dim rounded-pill bg-primary" title="Total Sub Categories">`+arr.length+`</span>`;
								arr.forEach((e) =>{
									console.log(e)
									txt +=`	<span class="badge badge-dim rounded-pill bg-gray" >`+e+`</span>`;
								})
								txt += `</span>`
								return txt;
							}else{
								if (data === null || data === undefined) {
									return "";
								} else {
									if(columnInfo.url){
										return `<a class="text-dark" href="${columnInfo.url + row[columnInfo.id]}" target="_blank">${data}</a>`;
									}else{
										return data;
									}
								}
							}
						}else{
							var action= `<a href="#" onclick="openInfoModal('${row[columnInfo.name]}')" class="btn btn-icon edit-color" th:title="Thông tin chi tiểt ${row[columnInfo.name]}">
											<em class="icon ni ni-eye"></em></a>`;
							if(columnInfo.delEdit){
								action += `<a href="#" onclick="openEditModal('${row[columnInfo.name]}')" class="btn btn-icon edit-color" th:title="Sửa thông tin ${row[columnInfo.name]}">
										<em class="icon ni ni-edit"></em></a>`;
								action += `<a href="${arrURL.urlDel + row[columnInfo.name]}" class="link-delete btn btn-icon remove-color" entityId="${row.id}" data-entityName="${row[columnInfo.name]}" title="Xóa ${row[columnInfo.name]}"> 
										<em class="icon ni ni-trash"></em></a>`;
							}
							return action;
						}
					}
				};
			}),
		};
		$(elm).on('draw.dt', function() {
			// cấu hình tr cho table. Lưu ý rằng có thể thêm vào tùy chọn
			// vị trí "text-? (left | right | justify | center)"
			// màu sắc "text-? (primary , success, dark, white, danger, warning, gray"
			// viết thường, in, chữ đầu in "text-?" (lowercase | uppercase | capitalize)
			// size "text-?px" (9 | 10 | 11 | 12 | 13 | 14 | 15 | 16)
			$(elm + ' thead tr').addClass('nk-tb-item nk-tb-head');
			$(elm + ' thead th').addClass('nk-tb-col tb-col-md text-center');
			$(elm + ' tbody tr').addClass('nk-tb-item');
			$(elm + ' tbody td').addClass('nk-tb-col tb-col-md');

			$(elm + ' tbody tr').each(function() {
				$(this).find('td').each(function(columnIndex) {
					var cellClassName = arr[columnIndex].className;
					if (cellClassName) {
						$(this).addClass(cellClassName);
					}
				});
			});
		});

		var mergedOptions = opt ? Object.assign({}, myExternalOptions, opt) : myExternalOptions;
		npl.gird(elm, mergedOptions);
	};


  // BootStrap Extended
  npl.BS.ddfix = function (elm, exc) {
    var dd = elm ? elm : '.dropdown-menu',
      ex = exc ? exc : 'a:not(.clickable), button:not(.clickable), a:not(.clickable) *, button:not(.clickable) *';
    $(dd).on('click', function (e) {
      if (!$(e.target).is(ex)) {
        e.stopPropagation();
        return;
      }
    });
    if (npl.State.isRTL) {
      var $dMenu = $('.dropdown-menu');
      $dMenu.each(function () {
        var $self = $(this);
        if ($self.hasClass('dropdown-menu-right') && !$self.hasClass('dropdown-menu-center')) {
          $self.prev('[data-toggle="dropdown"]').dropdown({
            popperConfig: {
              placement: 'bottom-start'
            }
          });
        } else if (!$self.hasClass('dropdown-menu-right') && !$self.hasClass('dropdown-menu-center')) {
          $self.prev('[data-toggle="dropdown"]').dropdown({
            popperConfig: {
              placement: 'bottom-end'
            }
          });
        }
      });
    }
  };

  // BootStrap Specific Tab Open
  npl.BS.tabfix = function (elm) {
    var tab = elm ? elm : '[data-toggle="modal"]';
    $(tab).on('click', function () {
      var _this = $(this),
        target = _this.data('target'),
        target_href = _this.attr('href'),
        tg_tab = _this.data('tab-target');
      var modal = target ? $body.find(target) : $body.find(target_href);
      if (tg_tab && tg_tab !== '#' && modal) {
        modal.find('[href="' + tg_tab + '"]').tab('show');
      } else if (modal) {
        var tabdef = modal.find('.nk-nav.nav-tabs');
        var link = $(tabdef[0]).find('[data-toggle="tab"]');
        $(link[0]).tab('show');
      }
    });
  };

  // Dark Mode Switch @since v2.0
  npl.ModeSwitch = function () {
    var toggle = $('.dark-switch');
    var isDarkMode = sessionStorage.getItem('darkMode') === 'enabled';
    if (isDarkMode) {
      toggle.addClass('active');
      $body.addClass('dark-mode');
    }
    toggle.on('click', function (e) {
      e.preventDefault();
      var enableDarkMode = !$(this).hasClass('active');
      $(this).toggleClass('active', enableDarkMode);
      $body.toggleClass('dark-mode', enableDarkMode);
      sessionStorage.setItem('darkMode', enableDarkMode ? 'enabled' : '');
    });
  };

  // Knob @v1.0
  npl.Knob = function (elm, opt) {
    if ($(elm).exists() && typeof $.fn.knob === 'function') {
      var def = {
          min: 0
        },
        attr = opt ? extend(def, opt) : def;
      $(elm).each(function () {
        $(this).knob(attr);
      });
    }
  };
  // Knob Init @v1.0
  npl.Knob.init = function () {
    var knob = {
      "default": {
        readOnly: true,
        lineCap: "round"
      },
      half: {
        angleOffset: -90,
        angleArc: 180,
        readOnly: true,
        lineCap: "round"
      }
    };
    npl.Knob('.knob', knob["default"]);
    npl.Knob('.knob-half', knob.half);
  };

  // Range @v1.0.1
  npl.Range = function (elm, opt) {
    if ($(elm).exists() && typeof noUiSlider !== 'undefined') {
      $(elm).each(function () {
        var $self = $(this),
          self_id = $self.attr('id');
        var _start = $self.data('start'),
          _start = /\s/g.test(_start) ? _start.split(' ') : _start,
          _start = _start ? _start : 0,
          _connect = $self.data('connect'),
          _connect = /\s/g.test(_connect) ? _connect.split(' ') : _connect,
          _connect = typeof _connect == 'undefined' ? 'lower' : _connect,
          _min = $self.data('min'),
          _min = _min ? _min : 0,
          _max = $self.data('max'),
          _max = _max ? _max : 100,
          _min_distance = $self.data('min-distance'),
          _min_distance = _min_distance ? _min_distance : null,
          _max_distance = $self.data('max-distance'),
          _max_distance = _max_distance ? _max_distance : null,
          _step = $self.data('step'),
          _step = _step ? _step : 1,
          _orientation = $self.data('orientation'),
          _orientation = _orientation ? _orientation : 'horizontal',
          _tooltip = $self.data('tooltip'),
          _tooltip = _tooltip ? _tooltip : false;
        console.log(_tooltip);
        var target = document.getElementById(self_id);
        var def = {
            start: _start,
            connect: _connect,
            direction: npl.State.isRTL ? "rtl" : "ltr",
            range: {
              'min': _min,
              'max': _max
            },
            margin: _min_distance,
            limit: _max_distance,
            step: _step,
            orientation: _orientation,
            tooltips: _tooltip
          },
          attr = opt ? extend(def, opt) : def;
        noUiSlider.create(target, attr);
      });
    }
  };

  // Range Init @v1.0
  npl.Range.init = function () {
    npl.Range('.form-control-slider');
    npl.Range('.form-range-slider');
  };
  npl.Select2.init = function () {
    // npl.Select2('.select');
    npl.Select2('.js-select2');
  };

  // Slick Slider @v1.0.1
  npl.Slick = function (elm, opt) {
    if ($(elm).exists() && typeof $.fn.slick === 'function') {
      $(elm).each(function () {
        var def = {
            'prevArrow': '<div class="slick-arrow-prev"><a href="javascript:void(0);" class="slick-prev"><em class="icon ni ni-chevron-left"></em></a></div>',
            'nextArrow': '<div class="slick-arrow-next"><a href="javascript:void(0);" class="slick-next"><em class="icon ni ni-chevron-right"></em></a></div>',
            rtl: npl.State.isRTL
          },
          attr = opt ? extend(def, opt) : def;
        $(this).slick(attr);
      });
    }
  };

  // Slick Init @v1.0
  npl.Slider.init = function () {
    npl.Slick('.slider-init');
  };

  // Magnific Popup @v1.0.0
  npl.Lightbox = function (elm, type, opt) {
    if ($(elm).exists()) {
      $(elm).each(function () {
        var def = {};
        if (type == 'video' || type == 'iframe') {
          def = {
            type: 'iframe',
            removalDelay: 160,
            preloader: true,
            fixedContentPos: false,
            callbacks: {
              beforeOpen: function beforeOpen() {
                this.st.image.markup = this.st.image.markup.replace('mfp-figure', 'mfp-figure mfp-with-anim');
                this.st.mainClass = this.st.el.attr('data-effect');
              }
            }
          };
        } else if (type == 'content') {
          def = {
            type: 'inline',
            preloader: true,
            removalDelay: 400,
            mainClass: 'mfp-fade content-popup'
          };
        } else {
          def = {
            type: 'image',
            mainClass: 'mfp-fade image-popup'
          };
        }
        var attr = opt ? extend(def, opt) : def;
        $(this).magnificPopup(attr);
      });
    }
  };

  // Controls @v1.0.0
  npl.Control = function (elm) {
    var control = document.querySelectorAll(elm);
    control.forEach(function (item, index, arr) {
      item.checked ? item.parentNode.classList.add('checked') : null;
      item.addEventListener("change", function () {
        if (item.type == "checkbox") {
          item.checked ? item.parentNode.classList.add('checked') : item.parentNode.classList.remove('checked');
        }
        if (item.type == "radio") {
          document.querySelectorAll('input[name="' + item.name + '"]').forEach(function (item, index, arr) {
            item.parentNode.classList.remove('checked');
          });
          item.checked ? item.parentNode.classList.add('checked') : null;
        }
      });
    });
  };

  // Number Spinner @v1.0
  npl.NumberSpinner = function (elm, opt) {
    var plus = document.querySelectorAll("[data-number='plus']");
    var minus = document.querySelectorAll("[data-number='minus']");
    plus.forEach(function (item, index, arr) {
      var parent = plus[index].parentNode;
      plus[index].addEventListener("click", function () {
        var child = plus[index].parentNode.children;
        child.forEach(function (item, index, arr) {
          if (child[index].classList.contains("number-spinner")) {
            var value = !child[index].value == "" ? parseInt(child[index].value) : 0;
            var step = !child[index].step == "" ? parseInt(child[index].step) : 1;
            var max = !child[index].max == "" ? parseInt(child[index].max) : Infinity;
            if (max + 1 > value + step) {
              child[index].value = value + step;
            } else {
              child[index].value = value;
            }
          }
        });
      });
    });
    minus.forEach(function (item, index, arr) {
      var parent = minus[index].parentNode;
      minus[index].addEventListener("click", function () {
        var child = minus[index].parentNode.children;
        child.forEach(function (item, index, arr) {
          if (child[index].classList.contains("number-spinner")) {
            var value = !child[index].value == "" ? parseInt(child[index].value) : 0;
            var step = !child[index].step == "" ? parseInt(child[index].step) : 1;
            var min = !child[index].min == "" ? parseInt(child[index].min) : 0;
            if (min - 1 < value - step) {
              child[index].value = value - step;
            } else {
              child[index].value = value;
            }
          }
        });
      });
    });
  };

  // Stepper @v1.0
  npl.Stepper = function (elm, opt) {
    var element = document.querySelectorAll(elm);
    if (element.length > 0) {
      element.forEach(function (item, index) {
        var def = {
            selectors: {
              nav: 'stepper-nav',
              progress: 'stepper-progress',
              content: 'stepper-steps',
              prev: 'step-prev',
              next: 'step-next',
              submit: 'step-submit'
            },
            classes: {
              nav_current: 'current',
              nav_done: 'done',
              step_active: 'active',
              step_done: 'done',
              active_step: 'active'
            },
            current_step: 1
          },
          attr = opt ? extend(def, opt) : def;
        npl.Custom.Stepper(item, attr);
        npl.Validate.OnChange('.js-select2');
        npl.Validate.OnChange('.date-picker');
        npl.Validate.OnChange('.js-tagify');
      });
    }
  };
  // Stepper Init @v1.0
  npl.Stepper.init = function () {
    npl.Stepper('.stepper-init');
  };

  // Tagify @v1.0.1
  npl.Tagify = function (elm, opt) {
    if ($(elm).exists() && typeof $.fn.tagify === 'function') {
      var def,
        attr = opt ? extend(def, opt) : def;
      $(elm).tagify(attr);
    }
  };
  // Tagify Init @v1.0
  npl.Tagify.init = function () {
    npl.Tagify('.js-tagify');
  };

  // Extra @v1.1
  npl.OtherInit = function () {
    npl.ClassBody();
    npl.PassSwitch();
    npl.CurrentLink();
    npl.LinkOff('.is-disable');
    npl.ClassNavMenu();
    npl.SetHW('[data-height]', 'height');
    npl.SetHW('[data-width]', 'width');
    npl.NumberSpinner();
    npl.Lightbox('.popup-video', 'video');
    npl.Lightbox('.popup-iframe', 'iframe');
    npl.Lightbox('.popup-image', 'image');
    npl.Lightbox('.popup-content', 'content');
    npl.Control('.custom-control-input');
  };

  // Animate Init @v1.0
  npl.Ani.init = function () {
    npl.Ani.formElm('.form-control-outlined');
    npl.Ani.formSearch('.toggle-search');
  };

  // BootstrapExtend Init @v1.0
  npl.BS.init = function () {
    npl.BS.menutip('a.nk-menu-link');
    npl.BS.tooltip('.nk-tooltip');
    npl.BS.tooltip('.btn-tooltip', {
      placement: 'top'
    });
    npl.BS.tooltip('[data-toggle="tooltip"]');
    npl.BS.tooltip('[data-bs-toggle="tooltip"]');
    npl.BS.tooltip('.tipinfo,.nk-menu-tooltip', {
      placement: 'right'
    });
    npl.BS.popover('[data-toggle="popover"]');
    npl.BS.popover('[data-bs-toggle="popover"]');
    npl.BS.progress('[data-progress]');
    npl.BS.fileinput('.form-file-input');
    npl.BS.modalfix();
    npl.BS.ddfix();
    npl.BS.tabfix();
  };

  // Picker Init @v1.0
  npl.Picker.init = function () {
	$.fn.datepicker.dates['en'].months = ["Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7","Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12"];
	$.fn.datepicker.dates['en'].monthsShort = ["Tg 1","Tg 2","Tg 3","Tg 4","Tg 5","Tg 6","Tg 7","Tg 8","Tg 9","Tg 10","Tg 11","Tg 12"];
	$.fn.datepicker.dates['en'].daysMin = ["CN","T2","T3","T4","T5","T6","T7"];
	$.fn.datepicker.dates['en'].clear = "Xóa";
   // npl.Picker.date('.date-picker');
    npl.Picker.dob('.date-picker-alt');
    npl.Picker.time('.time-picker');
    npl.Picker.date('.date-picker-range', {
      todayHighlight: true,
      autoclose: false,
      format: "dd/mm/yyyy",
    });
    npl.Picker.date('.date-picker', {
      todayHighlight: true,
      autoclose: true,
      format: "dd/mm/yyyy",
    });
    npl.Picker.date('.date-picker-ym', {
      format: "mm/yy",
      startView: 2,
      autoclose: true,
      maxViewMode: 2,
      minViewMode: 1
    });
  };

  // Addons @v1
  npl.Addons.Init = function () {
    npl.Knob.init();
    npl.Range.init();
    npl.Select2.init();
    npl.Dropzone.init();
    npl.Slider.init();
    npl.gird.init();
    npl.Tagify.init();
  };

  // Toggler @v1
  npl.TGL.init = function () {
    npl.TGL.content('.toggle');
    npl.TGL.expand('.toggle-expand');
    npl.TGL.expand('.toggle-opt', {
      toggle: false
    });
    npl.TGL.showmenu('.nk-nav-toggle');
    npl.TGL.ddmenu('.' + _menu + '-toggle', {
      self: _menu + '-toggle',
      child: _menu + '-sub'
    });
  };
  npl.BS.modalOnInit = function () {
    $('.modal').on('shown.bs.modal', function () {
      npl.Select2.init();
      npl.Validate.init();
    });
  };

  // Initial by default
  /////////////////////////////
  npl.init = function () {
    npl.coms.docReady.push(npl.OtherInit);
    npl.coms.docReady.push(npl.Prettify);
    npl.coms.docReady.push(npl.ColorBG);
    npl.coms.docReady.push(npl.ColorTXT);
    npl.coms.docReady.push(npl.Copied);
    npl.coms.docReady.push(npl.Ani.init);
    npl.coms.docReady.push(npl.TGL.init);
    npl.coms.docReady.push(npl.BS.init);
    npl.coms.docReady.push(npl.Validate.init);
    npl.coms.docReady.push(npl.Picker.init);
    npl.coms.docReady.push(npl.Addons.Init);
    npl.coms.docReady.push(npl.Wizard);
    npl.coms.docReady.push(npl.sbCompact);
    npl.coms.docReady.push(npl.Stepper.init);
    npl.coms.winLoad.push(npl.ModeSwitch);
  };
  npl.init();
  return npl;
}(npl, jQuery);