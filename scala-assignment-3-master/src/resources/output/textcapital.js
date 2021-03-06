VAR TEXTEDITOR = FUNCTION(MAIN){
	VAR TEXTDIV = CREATEELEMENT('DIV');
	TEXTDIV.SETATTRIBUTE('CONTENTEDITABLE', 'TRUE');

	VAR BUTTONS = {
		BOLDBTN: CREATEELEMENT('BUTTON', {'NAME': 'TEXT-B','TEXT-NAME': 'BOLD'}, 'BOLD'),
		ITALICBTN: CREATEELEMENT('BUTTON', {'NAME': 'TEXT-I', 'TEXT-NAME': 'ITALIC'}, 'ITALIC'),
		UNDERLINEBTN: CREATEELEMENT('BUTTON', {'NAME': 'TEXT-U', 'TEXT-NAME': 'UNDERLINE'}, 'UNDERLINE')
	}

	MAIN.APPENDCHILD(TEXTDIV);
	MAIN.APPENDCHILD(BUTTONS.BOLDBTN);
	MAIN.APPENDCHILD(BUTTONS.ITALICBTN);
	MAIN.APPENDCHILD(BUTTONS.UNDERLINEBTN);

	FUNCTION CREATEELEMENT(TYPE, ATTROBJ, HTML){
		VAR D = DOCUMENT.CREATEELEMENT(TYPE);
		IF(ATTROBJ && TYPEOF ATTROBJ === "OBJECT"){
			VAR KEYS = OBJECT.KEYS(ATTROBJ);
			FOR(ATTR IN ATTROBJ){
				D.SETATTRIBUTE(ATTR, ATTROBJ[ATTR]);
			}
			IF(HTML){
				D.INNERHTML = HTML;
			}
		}
		RETURN D;
	}
	FUNCTION DOWITHBUTTONS(PROPERTY, VALUE) {
	    FOR(BTN IN BUTTONS){
	    	BUTTONS[BTN][PROPERTY] = VALUE;
	    }
	}

	FUNCTION SELECTBUTTONS(E) {
	    VAR SEL = WINDOW.GETSELECTION();
	    IF (SEL.BASENODE && (SEL.BASENODE === TEXTDIV || SEL.BASENODE.OWNERDOCUMENT.ACTIVEELEMENT === TEXTDIV)) {
	        DOWITHBUTTONS("CLASSNAME", "");
	        VAR P = SEL.BASENODE.PARENTELEMENT;
	        WHILE (!/(DIV|BODY|BUTTON)/I.TEST(P.TAGNAME.TOLOWERCASE())) {
	            VAR BTN = DOCUMENT.GETELEMENTSBYNAME("TEXT-" + P.TAGNAME.TOLOWERCASE())[0];
	            BTN.CLASSNAME = "ACTIVE";
	            P = P.PARENTELEMENT;
	        }
	    } ELSE {
	        DOWITHBUTTONS("CLASSNAME", "");
	    }
	}

	FUNCTION ACTION(E) {
	    VAR EL = E.CURRENTTARGET;
	    DOCUMENT.EXECCOMMAND(EL.GETATTRIBUTE('TEXT-NAME'));
	    EL.CLASSNAME = (EL.CLASSNAME === "ACTIVE")? "":"ACTIVE";
	}
	TEXTDIV.ONFOCUS = SELECTBUTTONS;
	TEXTDIV.ONBLUR  = SELECTBUTTONS;
	TEXTDIV.ONKEYUP = SELECTBUTTONS;
	TEXTDIV.ONCLICK = SELECTBUTTONS;

	DOWITHBUTTONS("ONCLICK", ACTION);
}
VAR MAIN = DOCUMENT.GETELEMENTBYID("MAIN");
NEW TEXTEDITOR(MAIN);
