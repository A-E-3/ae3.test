var out;
{
	if(Object.consecutiveRun){
		// no output
		function nop(){
			// do nothing
		}
		out = new Object();
		out.print = out.println = nop;
	}else{
		// first run
		import java.lang.System;
		if( !System ){
			throw "import doesn't work! should be 'System' but: " + System;
		}
		out = System.out;
		if(!out){
			throw "static java object property is not accessible!";
		}
		if(!out.println){
			throw "java method is not accessible!";
		}
		out.println("Setting up system out from ECMA code.");
	}
	Object.consecutiveRun = true;
	if(!Object.consecutiveRun){
		throw "Cannot store value in Object";
	}
}

out.print("Checking system commonJs libraries...");
{
	var os = require("os");
	if('function' !== typeof os.hostname){
		throw "os.hostname is not a function!";
	}
}
out.println("done.");


out.print("Checking ae3sdk commonJs libraries...");
{
	const mac = require("ae3").net.mac.parseRanges('00:12:AA:12:45:01/2+00:12:AA:22:95:02/3').macAt(0).longString;
	if(mac !== '00:12:aa:12:45:01'){
		throw "ae3.net.mac is broken, 0, mac: " + mac;
	}
}
{
	const mac = require("ae3").net.mac.parseRanges('00:12:AA:12:45:01/2+00:12:AA:22:95:02/3').macAt(3).longString;
	if(mac !== '00:12:aa:22:95:03'){
		throw "ae3.net.mac is broken, 2, mac: " + mac;
	}
}
out.println("done.");

