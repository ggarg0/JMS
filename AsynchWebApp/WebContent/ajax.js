/*
 * creates a new XMLHttpRequest object which is the backbone of AJAX,
 * or returns false if the browser doesn't support it
 */
function getXMLHttpRequest() {
  var xmlHttpReq = false;
  // to create XMLHttpRequest object in non-Microsoft browsers
  if (window.XMLHttpRequest) {
    xmlHttpReq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
    try {
      // to create XMLHttpRequest object in later versions
      // of Internet Explorer
      xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (exp1) {
      try {
        // to create XMLHttpRequest object in older versions
        // of Internet Explorer
        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (exp2) {
        xmlHttpReq = false;
      }
    }
  }
  return xmlHttpReq;
}
/*
 * AJAX call starts with this function
 */
function makeSynchRequest() {
  var xmlHttpRequest = getXMLHttpRequest();
  xmlHttpRequest.onreadystatechange = getReadySynchStateHandler(xmlHttpRequest);
  xmlHttpRequest.open("POST", "AsynchAjax.do", true);
  xmlHttpRequest.setRequestHeader("Content-Type",
      "application/x-www-form-urlencoded");
  xmlHttpRequest.send(null);
}
 

function makeAsynchRequest() {
	  var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = getReadyAsynchStateHandler(xmlHttpRequest);
	  xmlHttpRequest.open("POST", "AsynchAjax.do", true);
	  xmlHttpRequest.setRequestHeader("Content-Type",
	      "application/x-www-form-urlencoded");
	  xmlHttpRequest.send(null);
	}


function getReadySynchStateHandler(xmlHttpRequest) {
 
  // an anonymous function returned
  // it listens to the XMLHttpRequest instance
  return function() {
    if (xmlHttpRequest.readyState == 4) {
    	
      if (xmlHttpRequest.status == 200) {
    	 
        document.getElementById("Synch").innerHTML = xmlHttpRequest.responseText;
      } else {
        alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
      }
    }
  };
}

function getReadyAsynchStateHandler(xmlHttpRequest) {
	 
	  // an anonymous function returned
	  // it listens to the XMLHttpRequest instance
	  return function() {
	    if (xmlHttpRequest.readyState == 4) {
	    	
	      if (xmlHttpRequest.status == 200) {
	    	 
	        document.getElementById("Asynch").innerHTML = xmlHttpRequest.responseText;
	      } else {
	        alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
	      }
	    }
	  };
	}