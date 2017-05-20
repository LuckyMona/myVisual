;(function(window) {

  var svgSprite = '<svg>' +
    '' +
    '<symbol id="icon-menu" viewBox="0 0 1353 1024">' +
    '' +
    '<path d="M225.46789 230.165138c0 35.699083 13.152294 37.577982 450.93578 37.577982 437.783486 0 450.93578-1.878899 450.93578-37.577982 0-35.699083-13.152294-37.577982-450.93578-37.577982-437.783486 0-450.93578 1.878899-450.93578 37.577982z"  ></path>' +
    '' +
    '<path d="M225.46789 512c0 35.699083 13.152294 37.577982 450.93578 37.577982 437.783486 0 450.93578-1.878899 450.93578-37.577982 0-35.699083-13.152294-37.577982-450.93578-37.577982-437.783486 0-450.93578 1.878899-450.93578 37.577982z"  ></path>' +
    '' +
    '<path d="M225.46789 793.834862c0 35.699083 13.152294 37.577982 450.93578 37.577982 437.783486 0 450.93578-1.878899 450.93578-37.577982 0-35.699083-13.152294-37.577982-450.93578-37.577982-437.783486 0-450.93578 1.878899-450.93578 37.577982z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-fenxi" viewBox="0 0 1402 1024">' +
    '' +
    '<path d="M1211.947207 0.000635c98.463118-0.250542 181.321031 73.62364 189.195217 168.686542 7.981561 96.45878-60.7744 180.712571-159.845978 196.675694-22.799348 3.686551-45.27657 3.221258-67.467458-1.753796-8.733188-1.968547-12.02603 0.572668-15.855747 7.050976-69.579172 117.325373-139.122552 234.686538-209.381767 351.618202-7.265726 12.097613 1.646421 13.922993 7.695227 18.611713 48.247286 37.295009 70.617133 86.114963 62.671363 145.386109-8.303687 61.955528-44.310193 105.299343-104.476133 127.633398-64.281992 23.837309-136.080253 4.366594-179.746194-47.066158-43.880692-51.683294-49.857915-122.944679-14.245119-180.605196 6.084598-9.842733 6.263557-14.101951-4.008677-21.475053-98.355743-70.831883-196.246193-142.308018-293.993476-213.92732-8.196312-6.013015-14.28091-7.265726-23.944684-2.720173-22.334055 10.451192-46.600865 14.459869-71.154009 10.916485-10.630151-1.539045-14.996745 1.861171-19.757049 9.914316-29.206072 49.714748-58.591103 99.32212-88.51301 148.643159-5.547722 9.162689-5.798264 14.603036 1.574837 23.873101 32.964206 41.411061 37.151842 86.651839 12.348155 133.216912-22.799348 42.771147-73.050972 68.648586-121.906718 62.277654-52.363338-6.836225-88.656177-34.896961-105.084593-84.289583-28.597612-86.150754 48.497828-171.048797 140.124721-154.477215 10.308025 1.861171 15.032537-0.966377 19.900216-9.305856 27.667027-47.388284 55.584596-94.669192 84.218-141.520599 6.227765-10.164858 6.084598-15.462038-3.507592-23.980476-45.348154-40.086766-58.412144-102.113877-34.324293-155.837301C236.478716 313.965911 292.313853 281.32383 353.124045 285.260923c57.696309 3.758134 108.019517 43.880692 124.447932 99.572663 11.059652 37.473968 6.299349 73.301514-13.994576 106.695222-5.368763 8.804772-3.829718 12.670281 3.972885 18.289587 99.071578 71.726677 197.964197 143.703896 296.749441 215.860075 6.514099 4.760303 11.274403 6.370932 19.255964 2.004338 27.344901-14.996745 57.231016-20.902385 88.369843-17.895878 11.131236 1.073753 15.676789-2.899132 20.79501-11.560737 68.612794-115.965286 137.476131-231.787406 206.768969-347.358983 6.227765-10.379609 6.478308-15.462038-4.116052-23.515183-63.100864-47.960952-87.117132-129.959863-60.917567-203.834045C1060.369125 50.216468 1132.274761 0.215386 1211.947207 0.000635L1211.947207 0.000635zM1211.947207 0.000635"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-down" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M489.4 613.9 195.2 319.7c-12.5-12.5-32.8-12.5-45.3 0l0 0c-12.5 12.5-12.5 32.8 0 45.3l316.8 316.8c25 25 65.5 25 90.5 0l316.8-316.8c12.5-12.5 12.5-32.8 0-45.3l0 0c-12.5-12.5-32.8-12.5-45.3 0L534.6 613.9C522.1 626.4 501.9 626.4 489.4 613.9z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-user" viewBox="0 0 1025 1024">' +
    '' +
    '<path d="M582.352706 568.885667c-3.173009-1.033003-23.208068-10.067029-10.688031-48.126141l-0.173001 0c32.628096-33.602098 57.568169-87.703257 57.568169-140.945413 0-81.88124-54.45416-124.788366-117.727345-124.788366-63.311185 0-117.455344 42.908126-117.455344 124.788366 0 53.458157 24.803073 107.775316 57.627169 141.295414 12.792037 33.563098-10.08603 46.021135-14.873044 47.77614-66.271194 23.96307-144.009422 67.648198-144.009422 110.771325l0 16.157047c0 58.755172 113.910334 72.111211 219.332643 72.111211 105.578309 0 218.047639-13.356039 218.047639-72.111211l0-16.157047C730.001139 635.229861 651.87491 591.897734 582.352706 568.885667L582.352706 568.885667zM582.352706 568.885667"  ></path>' +
    '' +
    '<path d="M511.308498 958.895809c-246.714723 0-447.417311-200.702588-447.417311-447.423311 0-246.714723 200.702588-447.417311 447.417311-447.417311 246.721723 0 447.423311 200.702588 447.423311 447.417311C958.731809 758.194221 758.029221 958.895809 511.308498 958.895809L511.308498 958.895809zM511.308498 128.259376c-211.307619 0-383.213123 171.905504-383.213123 383.213123 0 211.279619 171.905504 383.219123 383.213123 383.219123 211.279619 0 383.219123-171.939504 383.219123-383.219123C894.527621 300.165879 722.588117 128.259376 511.308498 128.259376L511.308498 128.259376zM511.308498 128.259376"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-iconfontwendang" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M87.611895 508.983293l49.52802-51.324946 372.570437 245.329475 371.908358-246.422366 50.176796 52.41886L509.710352 797.570991 87.611895 508.983293zM87.611895 350.010629 509.710352 61.416791 931.795507 350.010629 509.710352 638.597303 87.611895 350.010629zM509.710352 861.954347l371.908358-246.415202 50.176796 52.41272L509.710352 956.537516 87.611895 667.956981l49.52802-51.324946L509.710352 861.954347z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-info" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M512 227.555556c159.288889 0 284.444444 125.155556 284.444444 284.444444s-125.155556 284.444444-284.444444 284.444444-284.444444-125.155556-284.444444-284.444444S352.711111 227.555556 512 227.555556M512 170.666667C324.266667 170.666667 170.666667 324.266667 170.666667 512s153.6 341.333333 341.333333 341.333333 341.333333-153.6 341.333333-341.333333S699.733333 170.666667 512 170.666667L512 170.666667z"  ></path>' +
    '' +
    '<path d="M512 369.777778m-28.444444 0a0.5 0.5 0 1 0 56.888889 0 0.5 0.5 0 1 0-56.888889 0Z"  ></path>' +
    '' +
    '<path d="M512 711.111111c-17.066667 0-28.444444-11.377778-28.444444-28.444444L483.555556 483.555556C483.555556 466.488889 494.933333 455.111111 512 455.111111s28.444444 11.377778 28.444444 28.444444L540.444444 682.666667C540.444444 694.044444 529.066667 711.111111 512 711.111111z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-stacks" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M972.8 583.68l-414.72 204.8c-40.96 20.48-56.32 20.48-92.16 0l-414.72-204.8c-20.48-15.36-25.6-61.44-25.6-87.04C51.2 512 87.04 532.48 87.04 532.48l424.96 215.04 424.96-215.04c0 0 40.96-20.48 66.56-35.84C1003.52 522.24 993.28 573.44 972.8 583.68M972.8 363.52l-414.72 204.8c-40.96 20.48-56.32 20.48-92.16 0L51.2 363.52c-30.72-20.48-30.72-76.8 0-102.4l414.72-240.64c35.84-20.48 61.44-20.48 92.16 0L972.8 266.24C1003.52 281.6 1003.52 348.16 972.8 363.52M512 61.44 87.04 317.44 512 512l424.96-194.56L512 61.44M512 61.44M512 962.56l424.96-215.04c0 0 40.96-20.48 66.56-35.84 0 25.6-5.12 76.8-25.6 87.04l-414.72 204.8c-40.96 20.48-56.32 20.48-92.16 0l-414.72-204.8c-20.48-15.36-25.6-61.44-25.6-87.04 25.6 15.36 66.56 35.84 66.56 35.84L512 962.56"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-xiala" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M787.2 380.8c-9.6-9.6-22.4-12.8-35.2-12.8l-480 3.2c-12.8 0-25.6 3.2-35.2 12.8-19.2 19.2-19.2 48 0 67.2l240 240c0 0 0 0 0 0 0 0 0 0 0 0 3.2 3.2 9.6 6.4 12.8 9.6 0 0 3.2 3.2 3.2 3.2 16 6.4 38.4 3.2 51.2-9.6l240-243.2C806.4 428.8 803.2 400 787.2 380.8z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-xiangmu" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M625.679086 66.855664c79.770811 0 159.542646 0 239.313457 0 1.64036 0.393973 3.26537 0.883113 4.924149 1.169639 46.188972 7.962346 80.865749 42.580794 88.890516 88.76465 0.542352 3.120061 1.103124 6.237051 1.655709 9.356089 0 76.801177 0 153.602354 0 230.402507-0.364297 1.647523-0.838088 3.279696-1.076518 4.945639-7.879458 55.216579-52.505841 94.200452-108.339474 94.298689-70.38914 0.12382-140.779304 0.124843-211.168444 0.001023-61.175291-0.106424-108.996436-46.844911-109.499903-108.060111-0.584308-71.02052-0.480954-142.052297-0.014326-213.07384 0.312108-47.523363 28.887934-86.652546 73.129554-102.313224C610.728582 69.786413 618.325607 68.849064 625.679086 66.855664zM745.378282 405.434939c34.351366 0 68.701708 0.019443 103.053074-0.008186 15.897062-0.01228 21.642926-5.627161 21.649066-21.276582 0.029676-68.701708 0.030699-137.403416-0.002047-206.105125-0.007163-15.299451-6.262634-21.694091-21.668509-21.711488-68.701708-0.080841-137.403416-0.080841-206.105125 0-15.32708 0.01842-21.671579 6.515391-21.678742 21.753443-0.030699 68.701708-0.029676 137.403416-0.001023 206.105125 0.00614 15.393595 5.794983 21.214161 21.063735 21.230534C676.252925 405.460522 710.815091 405.433916 745.378282 405.434939z"  ></path>' +
    '' +
    '<path d="M400.367527 959.189925c-79.770811 0-159.542646 0-239.313457 0-1.851161-0.420579-3.689019-0.927115-5.557576-1.25048-45.740764-7.91732-80.891332-43.434232-88.37477-89.310072-0.475837-2.915399-1.02433-5.819542-1.539053-8.728802 0-76.801177 0-153.602354 0-230.402507 0.367367-1.647523 0.845251-3.27765 1.084705-4.943592 7.847735-54.683437 51.783387-93.839225 107.111506-94.202499 71.234391-0.467651 142.475945-0.483001 213.709313-0.00307 60.649312 0.408299 107.742886 47.378054 108.193141 108.021226 0.529049 71.233368 0.89437 142.484132-0.159636 213.706243-0.659009 44.506656-22.143323 77.310783-62.371536 97.321536C422.765653 954.562535 411.544077 956.763666 400.367527 959.189925zM280.749172 620.612697c-34.561144 0-69.122287-0.028653-103.683431 0.013303-15.298428 0.01842-21.062711 5.825682-21.067828 21.212114-0.024559 68.698638-0.024559 137.396253 0 206.094892 0.005117 15.281031 6.30152 21.765723 21.611204 21.783119 68.698638 0.077771 137.396253 0.075725 206.094892 0.00307 15.35164-0.016373 21.709441-6.483668 21.716604-21.692045 0.029676-68.698638 0.028653-137.396253 0.00307-206.094892-0.00614-15.649422-5.754051-21.296025-21.626553-21.310352C349.446787 620.590184 315.098491 620.611673 280.749172 620.612697z"  ></path>' +
    '' +
    '<path d="M65.583694 396.548548c0-76.801177 0-153.602354 0-230.402507 0.400113-2.069125 0.862647-4.129041 1.191128-6.209422 5.821589-36.846186 25.079188-64.0999 58.090023-81.610716 11.380188-6.036483 23.727401-8.906857 36.189224-11.469216 79.770811 0 159.542646 0 239.313457 0 1.641383 0.396019 3.266393 0.88516 4.927219 1.173732 52.343135 9.086959 90.039689 52.653197 90.393753 105.97359 0.472767 71.236438 0.523933 142.481062-0.007163 213.715453-0.448208 60.024072-47.564295 107.563808-106.937544 107.972107-72.083735 0.49528-144.175657 0.471744-216.260415-0.00307-52.399417-0.344854-96.093569-38.188764-105.184621-89.790002C66.748217 402.777413 66.155722 399.664515 65.583694 396.548548zM280.524045 405.433916c34.561144 0 69.122287 0.023536 103.683431-0.011256 15.377222-0.01535 21.206998-5.786797 21.213137-21.072945 0.028653-68.698638 0.027629-137.396253 0.001023-206.094892-0.00614-15.33322-6.291287-21.900799-21.496593-21.920242-68.910463-0.084934-137.819902-0.089028-206.730365 0.00921-14.84408 0.021489-21.188578 6.6607-21.194718 21.559015-0.027629 68.910463-0.028653 137.820925 0.001023 206.730365 0.00614 14.907525 5.892197 20.766976 20.839631 20.784372C211.401758 405.461545 245.962901 405.432892 280.524045 405.433916z"  ></path>' +
    '' +
    '<path d="M864.992543 959.189925c-79.770811 0-159.542646 0-239.313457 0-1.222851-0.336668-2.427282-0.773619-3.670599-0.99363-54.258765-9.645684-91.227748-52.083216-91.636047-106.880239-0.529049-71.067592-0.60989-142.147464 0.020466-213.21301 0.536212-60.46614 47.53462-107.285468 107.934245-107.738793 71.28044-0.535189 142.572136-0.64366 213.849506 0.068562 42.22673 0.421602 74.096579 20.132526 95.290273 56.748469 7.570419 13.078877 10.56973 27.625174 12.995989 42.316781 0 76.801177 0 153.602354 0 230.402507-0.375553 1.860371-0.829901 3.708462-1.11438 5.583159-5.761214 37.929868-25.612331 65.658396-59.85318 83.126232C888.578728 954.177771 876.795357 956.651103 864.992543 959.189925zM870.093724 744.654804c0-34.138518 0.023536-68.277036-0.010233-102.415554-0.016373-15.874549-5.658883-21.609157-21.323655-21.615297-68.913533-0.026606-137.827065-0.026606-206.740598-0.001023-15.696494 0.00614-21.390169 5.743818-21.395286 21.553898-0.025583 68.489884-0.026606 136.978744 0 205.468628 0.00614 15.670911 6.26775 22.062482 21.99085 22.077831 68.488861 0.067538 136.978744 0.067538 205.468628 0 15.815197-0.01535 21.98164-6.296403 21.999037-22.016433C870.121354 813.356512 870.093724 779.00617 870.093724 744.654804z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-shangla1" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M553.504 356.936c-9.394-9.396-22.208-14.722-35.522-14.722s-26.078 5.278-35.522 14.722l-233.742 233.742c-9.598 9.597-14.722 22.459-14.722 35.522 0 6.482 1.259 13.015 3.82 19.245 7.785 18.74 26.126 30.999 46.425 30.999h467.43c20.297 0 38.636-12.207 46.425-30.999 7.786-18.842 3.517-40.344-10.905-54.766l-233.686-233.745z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '</svg>'
  var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
  var shouldInjectCss = script.getAttribute("data-injectcss")

  /**
   * document ready
   */
  var ready = function(fn) {
    if (document.addEventListener) {
      if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
        setTimeout(fn, 0)
      } else {
        var loadFn = function() {
          document.removeEventListener("DOMContentLoaded", loadFn, false)
          fn()
        }
        document.addEventListener("DOMContentLoaded", loadFn, false)
      }
    } else if (document.attachEvent) {
      IEContentLoaded(window, fn)
    }

    function IEContentLoaded(w, fn) {
      var d = w.document,
        done = false,
        // only fire once
        init = function() {
          if (!done) {
            done = true
            fn()
          }
        }
        // polling for no errors
      var polling = function() {
        try {
          // throws errors until after ondocumentready
          d.documentElement.doScroll('left')
        } catch (e) {
          setTimeout(polling, 50)
          return
        }
        // no errors, fire

        init()
      };

      polling()
        // trying to always fire before onload
      d.onreadystatechange = function() {
        if (d.readyState == 'complete') {
          d.onreadystatechange = null
          init()
        }
      }
    }
  }

  /**
   * Insert el before target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var before = function(el, target) {
    target.parentNode.insertBefore(el, target)
  }

  /**
   * Prepend el to target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var prepend = function(el, target) {
    if (target.firstChild) {
      before(el, target.firstChild)
    } else {
      target.appendChild(el)
    }
  }

  function appendSvg() {
    var div, svg

    div = document.createElement('div')
    div.innerHTML = svgSprite
    svgSprite = null
    svg = div.getElementsByTagName('svg')[0]
    if (svg) {
      svg.setAttribute('aria-hidden', 'true')
      svg.style.position = 'absolute'
      svg.style.width = 0
      svg.style.height = 0
      svg.style.overflow = 'hidden'
      prepend(svg, document.body)
    }
  }

  if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
    window.__iconfont__svg__cssinject__ = true
    try {
      document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
    } catch (e) {
      console && console.log(e)
    }
  }

  ready(appendSvg)


})(window)