/**
 * 
 */
// test ecma script
// version 1.1f

// null.null();
// return dasdsa?.();
// return dasdsa?.(true);
// return dasdsa?.(true,1,x);

// null.null();
// return options.sda.sd.sa();
// return options.sda.sdsa();
// return options[sda()]();
// return options.sda[sda()]();
// return options.sdasdsa();
// return options.sda.sdsa?.();
// return options?.sdasdsa?.();

// null.null();
// return options.sda.sd.sa({},{});
// return options.sda.sdsa({},{});
// return options[sda()]({},{});
// return options.sda[sda()]({},{});
// return options.sdasdsa({},{});
// return options.sda.sdsa?.({},{});
// return options?.sdasdsa?.({},{});


if(false){
	throw "if doesn't work!";
}

if(undefined){
	throw "if doesn't work!";
}

if(undefined != null){
	throw "if doesn't work!";
}

if(undefined == false){
	throw "if doesn't work!";
}

if(undefined == 0){
	throw "if doesn't work!";
}

if(!Object){
	throw "Object is not defined!";
}

if(!___ECMA_IMPL___) throw "ecma impl: " + ___ECMA_IMPL___;
if(!___ECMA_IMPL_VERSION_STRING___) throw "ecma impl: " + ___ECMA_IMPL_VERSION_STRING___;
if(!___ECMA_IMPL_VERSION_NUMBER___) throw "ecma impl: " + ___ECMA_IMPL_VERSION_NUMBER___;

if(!Object.prototype){
	throw "Object.prototype is not defined!";
}

if(!Object.valueOf){
	throw "Object.valueOf is not defined!";
}

if(!Object.toString){
	throw "Object.toString is not defined!";
}

{
	var a = 5;
	if(a != 5){
		throw "Variable doesn't work in own scope!";
	}
	{
		if(a != 5){
			throw "Variable doesn't work in nested scope!";
		}
		a = 6;
		if(a != 6){
			throw "Variable change doesn't work in nested scope!";
		}
	}
}

if(Ubject){
	throw "Ubject is like defined - but should not - does 'if' still work?";
}

{
	/**
	 * check if operator
	 */
	if( undefined ){
		throw "undefined value is true!";
	}
	{
		if( a ){
			throw "a should be undefined but: " + a;
		}
		var a;
		if( a ){
			throw "a should be undefined but: " + a;
		}
		a = 5;
		if( !a ){
			throw "number 5 is false!";
		}
		if( a ){
			a = 5;
		}else{
			throw "number 5 is false!";
		}
	}
	{
		/** 
		 * without braces
		 */
		var a = 5;
		if( !a )
			throw "number 5 is false!";

		// single line
		if( a ) a = 5;
		if( a ) a = 5; else throw "number 5 is false!";
		if( a ) a = 5; else { throw "number 5 is false!"; }
		if( a ) { a = 5; } else throw "number 5 is false!";

		// multiple lines
		if( a )
			a = 5;
		else {
			throw "number 5 is false!";
		}

		if( a ) {
			a = 5;
		} else
			throw "number 5 is false!";

		if( a )
			a = 5;
		else
			throw "number 5 is false!";
	}
	{
		if( a ){
			throw "a should be undefined but: " + a;
		}
		{
			var a;
			{
				a = 5;
			}
			if( a != 5 ){
				throw "a should be equal to 5 but: " + a;
			}
		}
		if( a ){
			throw "a should be undefined but: " + a;
		}
	}
	{
		{
			var a;
			{
				var b = 5;
				a = b + 1;
				if( a != 6 ){
					throw "[0] a should be equal to 6 but: " + a;
				}
			}
			if( a != 6 ){
				throw "[1] a should be equal to 6 but: " + a;
			}
		}
		if( a ){
			throw "a should be undefined but: " + a;
		}
	}
	{
		var c = 1;
		if(true){
			c = 2;
		}
		if(c != 2){
			throw "if(true) doesn't work!";
		}
	}
	{
		/**
		 * setLocals
		 */
		{
			var a = 1, b, c;
			if("undefined" !== typeof d){
				throw "Must be undefined";
			}
			if("undefined" !== typeof e){
				throw "Must be undefined";
			}
			if(a == 1) var d = 5;
			if(a == 0) var e = 5;
			if("undefined" === typeof d){
				throw "Must not be undefined";
			}
			if("undefined" !== typeof e){
				throw "Must be undefined";
			}
		}
		if("undefined" !== typeof d){
			throw "Must be undefined";
		}
	}
}

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


out.print("String concatenation and functions...");
{
	var a = "12";
	var b = "34";
	var c = a + b;
	if( c != "1234" ){
		throw "'12' + '34' == " + c;
	}
	if( c.length != 4 ){
		var d = c.length;
		var e = ('12' + '34').length;
		var f = d == 4;
		var g = e == 4;
		var h = d == e;
		throw new Error("('12' + '34').length == " + c.length);
	}
	if( c.length() != 4 ){
		throw "('12' + '34').length() == " + c.length();
	}
}
out.println("done.");

out.print("Template literals...");
{
	if( `abc` !== "abc" ){
		throw "template plain: " + `abc`;
	}
	if( `` !== "" ){
		throw "template empty: [" + `` + "]";
	}
	{
		var name = "file";
		if( `${name}.xls` !== "file.xls" ){
			throw "template subst: " + `${name}.xls`;
		}
	}
	if( `AB${ 'C' + 'D' }EF` !== "ABCDEF" ){
		throw "template expr: " + `AB${ 'C' + 'D' }EF`;
	}
	if( `a${1+2}b${3+4}c` !== "a3b7c" ){
		throw "template multi: " + `a${1+2}b${3+4}c`;
	}
	if( `semi; and ${ 'brace' } {here}` !== "semi; and brace {here}" ){
		throw "template punctuation: " + `semi; and ${ 'brace' } {here}`;
	}
	{
		var o = { v : `x;${ 'Q' }y` };
		if( o.v !== "x;Qy" ){
			throw "template in object: " + o.v;
		}
	}
}
out.println("done.");

out.print("String unicode...");
{
	{
		var a = "1\u00202";
		var b = "1 2";
		if( a != b ){
			throw "Unicode error: " + a + " != " + b;
		}
	}
	{
		var a = "\u00202";
		var b = " 2";
		if( a != b ){
			throw "Unicode error: " + a + " != " + b;
		}
	}
	{
		var a = "1\u0020";
		var b = "1 ";
		if( a != b ){
			throw "Unicode error: " + a + " != " + b;
		}
	}
}
out.println("done.");

out.print("Check NaN...");
{
	if(undefined == Number.NaN){
		throw "Number.NaN is undefined, value=" + Number.NaN;
	}
	if(!isNaN(Number.NaN)){
		throw "Number.NaN is not NaN, Number.NaN = " + Number.NaN;
	}
	if(Number.NaN >= 0){
		throw "Number.NaN should compare false with everything";
	}
	if(Number.NaN <= 0){
		throw "Number.NaN should compare false with everything";
	}
	if(Number.NaN > 0){
		throw "Number.NaN should compare false with everything";
	}
	if(Number.NaN < 0){
		throw "Number.NaN should compare false with everything";
	}
	if(Number.NaN == 0){
		throw "Number.NaN should compare false with everything";
	}
	if(!(Number.NaN != 0)){
		throw "Number.NaN should compare false with everything";
	}
	if(Number.NaN == Number.NaN){
		throw "Number.NaN must not be equal to itself";
	}
}
out.println("done.");

out.print("Check attributes of properties...");
{
	if(Math.propertyIsEnumerable('random')){
		throw "Should not be enumerable!";
	}
	{
		var o = {};
		if(o.propertyIsEnumerable('toString')){
			throw "Should not be enumerable!";
		}
	}
	{
		var o = {
			prop : 'string'
		};
		if(o.propertyIsEnumerable('toString')){
			throw "Should not be enumerable!";
		}
		if(!o.propertyIsEnumerable('prop')){
			throw "Should be enumerable!";
		}
	}
}
out.println("done.");

out.print("Check 'with'...");
{
	var bbb = 1;
	with({
		aaa : 1
	}){
		var ccc = 1;
		var eee = 2, fff = 3, ggg = 4;
		var hhh, iii, jjj;
		if(ddd == 1){
			throw "non-existent context variable is visible!";
		}
		if(aaa != 1){
			throw "object's context variable is hidden or not visible!";
		}
		if(ccc != 1){
			throw "local context variable is hidden or not visible!";
		}
		if(bbb != 1){
			throw "parent context variable is hidden!";
		}
	}
}
out.println("done.");

out.print("Check 'var' statement on existing context property...");
{
	/**
	 * {} object will definitely have .size extra method defined.
	 */
	with({}){
		if(size == 5){
			throw "'size' should not be 5 at this point yet!";
		}
		{
			var size = 5;
			if(size != 5){
				throw "predefined context property cannot be overriden locally!";
			}
		}
		if(size == 5){
			throw "'size' should not be 5 at this point already!";
		}
	}
}
out.println("done.");

out.print("Check NaN statement on existing context property...");
{
	if(!isNaN){
		throw "'isNaN' property should be defined!";
	}
	if(!(typeof isNaN == "function")){
		throw "'isNaN' property should contain function!";
	}
	if(isNaN == 5){
		throw "'isNaN' should not be 5 at this point yet!";
	}
	if(false){
		isNaN = 5;
		if(isNaN == 5){
			throw new Error("'isNaN' should not be 5 at this point - not writable!");
		}
	}
	{
		var isNaN = 5;
		if(isNaN != 5){
			throw "predefined context property cannot be overriden locally!";
		}
	}
	if(isNaN == 5){
		throw "'isNaN' should not be 5 at this point already!";
	}
	if(!isNaN(Number.NaN)){
		throw "'isNaN' function is broken on exit!";
	}
}
out.println("done.");

out.print("BRACE expression flow...");
if(___ECMA_IMPL_VERSION_NUMBER___ > 677){
	{
		var a = (1, 2, 3, 4, 5);
		if(a != 5){
			throw "expected to be 5, but: " + a;
		}
	}
	{
		var b = 1, c = 2, d = 3, e = 4, f = 5;
		var a = (b, c, d, e, f);
		if(a != 5){
			throw "expected to be 5, but: " + a;
		}
	}
	{
		function test(b,c,d,e,f){
			var a = (b, c, d, e, f);
			if(a != f){
				throw "expected to be equal to 'f', but: a = " + a + ", f = " + f;
			}
			return a;
		}
		var a = test(1,2,3,4,5);
		if(a != 5){
			throw "expected to be 5, but: " + a;
		}
	}
}
out.println("done.");

out.print("BOR BAND boolean expression flow...");
{
	if(0 && 1 !== 0){
		throw "&& broken, expected 0, but: " + (0 && 1);
	}
	if('' && 1 !== ''){
		throw "&& broken, expected '', but: " + ('' && 1);
	}
	if(null && 1 !== null){
		throw "&& broken, expected null, but: " + (null && 1);
	}
	if(false && 1 !== false){
		throw "&& broken, expected false, but: " + (false && 1);
	}
	if(undefined && 1 !== undefined){
		throw "&& broken, expected undefined, but: " + (undefined && 1);
	}
}
{
	if(true && 1 !== 1){
		throw "&& broken, expected 1, but: " + (true && 1);
	}
	if(2 && 1 !== 1){
		throw "&& broken, expected 1, but: " + (true && 1);
	}
	if("TRUE" && 1 !== 1){
		throw "&& broken, expected 1, but: " + (true && 1);
	}
}
{
	var x = 0, z = 0;
	x && (z = 1);
	if(z != 0){
		throw "Checkpoint, not zero: " + z;
	}
}
{
	var x = 2, z = 0;
	x == 1 ? (z = 1) : 15;
	if(z != 0){
		throw "Checkpoint, not zero: " + z;
	}
}
{
	var x = 2, z = 0;
	x == 1 && (z = 1);
	if(z != 0){
		throw "Checkpoint, not zero: " + z;
	}
}
{
	var count = 1;
	var open01 = true;
	var open02 = true;
	var result = count + (open01 && open02);
	if(result != 2){
		throw "Have " + result + " instead of 2!";
	}
}
{
	var count = 1;
	var open11 = true;
	var open12 = true;
	var result = count + (open11 || open12);
	if(result != 2){
		throw "Should (1) be equal to 2";
	}
}
{
	var count = 1;
	var open21 = true;
	var open22 = false;
	var result = count + (open21 || open22);
	if(result != 2){
		throw "Should (2) be equal to 2";
	}
}
{
	var count = 1;
	var open31 = false;
	var open32 = true;
	var result = count + (open31 || open32);
	if(result != 2){
		throw "Should (3) be equal to 2, but: " + result;
	}
}
{
	var count = 2;
	var open41 = true;
	var open42 = false;
	var result = count + (open41 && open42);
	if(result != 2){
		throw "Should (4) be equal to 2, but: " + result;
	}
}
{
	var count = 2;
	var open51 = false;
	var open52 = true;
	var result = count + (open51 && open52);
	if(result != 2){
		throw "Should (5) be equal to 2";
	}
}
{
	var count = 2;
	var open61 = false;
	var open62 = false;
	var result = count + (open61 && open62);
	if(result != 2){
		throw "Should (6) be equal to 2";
	}
}
{
	var count = 2;
	var open71 = false;
	var open72 = false;
	var result = count + (open71 || open72);
	if(result != 2){
		throw "Should (7) be equal to 2";
	}
}
{
	var cx = 3;
	var cy = 2;
	var fieldSize = 3;
	var result = cx >= 0 && cy >= 0 && cx < fieldSize;
	if(result){
		throw "Should be true";
	}
}
{
	var cx = 3;
	var cy = -1;
	var fieldSize = 3;
	var result = cx >= 0 && cy >= 0 && cx < fieldSize;
	if(result){
		throw "Should be true";
	}
}
{
	var cx = 3;
	var cy = 2;
	var fieldSize = 3;
	if(cx >= 0 && cy >= 0 && cx < fieldSize){
		throw;
	}
}
{
	var cx = 3;
	var cy = -1;
	var fieldSize = 3;
	if(cx >= 0 && cy >= 0 && cx < fieldSize){
		throw;
	}
}
{
	var cx = 3;
	var cy = 2;
	var fieldSize = 3;
	var result = cx >= 0 && cy >= 0 && cx < fieldSize && cy < fieldSize;
	if(result){
		throw "Should be true";
	}
}
{
	var cx = 3;
	var cy = 2;
	var fieldSize = 3;
	if(cx >= 0 && cy >= 0 && cx < fieldSize && cy < fieldSize){
		throw;
	}
}
{
	var key = "layoutMap";
	var path = "bbb";
	if(key != null && path != null && key != 'layoutMap'){
		throw;
	}
}
out.println("done.");

out.print("'in' operator...");
{
	var o = { aaa : 5, bbb : 7 };
	if(!('aaa' in o)){
		throw "'in' operator failure";
	}
	if(!('bbb' in o)){
		throw "'in' operator failure";
	}
	if(!('toString' in o)){
		throw "'in' operator failure";
	}
	if('ccc' in o){
		throw "'in' operator failure";
	}
}
{
	var o = [ aaa, 5, bbb, 7 ];
	if(!('length' in o)){
		throw "'in' operator failure";
	}
	if(!(0 in o)){
		throw "'in' operator failure";
	}
	if(!(1 in o)){
		throw "'in' operator failure";
	}
	if(9 in o){
		throw "'in' operator failure";
	}
}
out.println("done.");

out.print("'instanceof' operator...");
{
	var o = { aaa : 5, bbb : 7 };
	if(!(o instanceof Object)){
		throw new Error("'instanceof' operator failure");
	}
	if(o instanceof String){
		throw new Error("'instanceof' operator failure");
	}

	var a = [aaa, 5, bbb, 7];
	if(!(a instanceof Array)){
		throw new Error("'instanceof' operator failure");
	}
	if(a instanceof String){
		throw new Error("'instanceof' operator failure");
	}

	var d = new Date();
	if(!(d instanceof Date)){
		throw new Error("'instanceof' operator failure");
	}
	if(d instanceof String){
		throw new Error("'instanceof' operator failure");
	}

}
{
	import java.lang.StringBuilder;
	var s = new StringBuilder();
	if(!(s instanceof StringBuilder)){
		throw new Error("'instanceof' operator failure");
	}
	if("aaa" instanceof StringBuilder){
		throw new Error("'instanceof' operator failure");
	}
	if(s instanceof String){
		throw new Error("'instanceof' operator failure");
	}
	if(s instanceof Date){
		throw new Error("'instanceof' operator failure");
	}
	if(s instanceof Boolean){
		throw new Error("'instanceof' operator failure");
	}
	if(s instanceof Number){
		throw new Error("'instanceof' operator failure");
	}
}
{
	/**
	 * MDC
	 */
	function Car(make, model, year)
	{
	  this.make = make;
	  this.model = model;
	  this.year = year;
	}
	var mycar = new Car("Honda", "Accord", 1998);
	var a = mycar instanceof Car;    // returns true
	var b = mycar instanceof Object; // returns true
	
	var c = mycar instanceof Array; // should be false
	
	if(!a){
		throw new Error("'instanceof' operator failure");
	}
	
	if(!b){
		throw new Error("'instanceof' operator failure");
	}
	
	if(c){
		throw new Error("'instanceof' operator failure");
	}
}
out.println("done.");

out.print("INLINE FUNCTION...");
{
	var func = function(){return 5;};
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * just to play with parser
	 */
	var func = function() { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * just to play with parser 2
	 */
	var func = function () { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * just to play with parser 2
	 */
	var func = function f() { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * just to play with parser 2
	 */
	var func = function f () { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * just to play with parser 3
	 */
	var func = function ( ) { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * variation with one parameter declared
	 */
	var func = function ( a ) { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * variation with one parameter passed
	 */
	var func = function ( ) { return 5; };
	
	if( 5 != func('aa') ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * variation with one parameter declared 2
	 */
	var func = function f ( a ) { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * variation with one parameter passed 2
	 */
	var func = function f ( ) { return 5; };
	
	if( 5 != func('aa') ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * variation with one parameter declared 3
	 */
	function func ( a ) { return 5; };
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
{
	/**
	 * variation with one parameter passed 3
	 */
	function func ( ) { return 5; };
	
	if( 5 != func('aa') ){
		throw "inline function doesn't work!";
	}
}
{
	var func = function(a, b){
		return a+b+5;
	};
	
	if( 11 != func(2, 4) ){
		throw "inline function arguments do not work!";
	}
}
{
	var func = function(a, b){
		return [a,b];
	};
	
	if( !func(2, 4) ){
		throw "inline function cannot return array, result: " + func(2, 4);
	}
	
	if( !func(2, 4).length ){
		throw "inline function cannot return array, result: " + func(2, 4);
	}
	
	if( func(2, 4)[0] != 2 ){
		throw "inline function arguments do not work!";
	}
	
	if( func(2, 4)[1] != 4 ){
		throw "inline function arguments do not work!";
	}
}
{
	var func = function sameAsAboveButWithName(a, b){
		return [a,b];
	};
	
	if( !func(2, 4) ){
		throw "inline function cannot return array, result: " + func(2, 4);
	}
}
{
	var func = function sameAsAboveButWithName(a, b){
		return [a,b];
	};
	
	/**
	 * Call as map!
	 */
	if( !func(a = 2, b = 4) ){
		throw "inline function cannot return array, result: " + func(2, 4);
	}
}
{
	var func = function(a, b, c, d, e, f){
		return a + b + c + d + e + f - 5;
	};
	
	if( 11 != func(2, 4, 1, 2, 3, 4) ){
		throw "inline function arguments do not work!";
	}
}
{
	var func = function sameAsAboveButWithName(a, b, c, d, e, f){
		return a + b + c + d + e + f - 5;
	};
	
	if( 11 != func(2, 4, 1, 2, 3, 4) ){
		throw "inline function arguments do not work!";
	}
}
{
	var func = function(a, b){
		return function(){
			return 5 + a + b;
		};
	};
	
	var r1 = func(2, 4);
	if( "function" != typeof r1){
		throw "expected to return function result, but: " + r1;
	}
	var r2 = r1();
	if( 11 != r2 ){
		throw "inline function arguments do not work, expected 11, but: " + r2;
	}
}
out.println("done.");

out.print("INLINE function parser 1...");
{
	/**
	 * more parser checks
	 */
	{
		for(var i=10;i>0;i--){
		}
		function vlapanError() {
		}
	}
	{
		if(true){
		}
		function vlapanError() {
		}
	}
	{
		while(false){
		}
		function vlapanError() {
		}
	}
	{
		var test = false;
		do{
		}while(test);
		function vlapanError() {
		}
	}
	{
		var test = false;
		do{
		}while(test)
		function vlapanError() {
		}
	}
	{
		$output(null){
		}
		function vlapanError() {
		}
	}
}
out.println("done.");

out.print("INLINE function parser 2 and 'arguments' object...");
{
	/**
	 * arguments object
	 */
	for each(var func in [
	              		function(a, b){
	            			if(a != 3) throw "wrong argument, expected 3, but: " + a;
	            			if(b != 5) throw "wrong argument, expected 5, but: " + b;
	            			if(!arguments) throw "arguments object is not defined";
	            			if(arguments[0] != 3) throw "wrong argument, expected 3, but: " + arguments[0];
	            			if(arguments.length != 2) throw "wrong argument count, expected 2, but: " + arguments.length;
	            		},
	              		function(a){
	            			if(a != 3) throw "wrong argument, expected 3, but: " + a;
	            			if(!arguments) throw "arguments object is not defined";
	            			if(arguments[0] != 3) throw "wrong argument, expected 3, but: " + arguments[0];
	            			if(arguments.length != 2) throw "wrong argument count, expected 2, but: " + arguments.length;
	            		},
	              		function(){
	            			if(!arguments) throw "arguments object is not defined";
	            			if(arguments[0] != 3) throw "wrong argument, expected 3, but: " + arguments[0];
	            			if(arguments.length != 2) throw "wrong argument count, expected 2, but: " + arguments.length;
	            		},
	                      ]){
		
		func(3, 5);
		func.call(null, 3, 5);
		func.apply(null, [3, 5]);
		func.call({}, 3, 5);
		func.apply({}, [3, 5]);
		
		{
			function check(a, b){
				if(a != 3) throw "wrong argument, expected 3, but: " + a;
				if(b != 5) throw "wrong argument, expected 5, but: " + b;
				if(!arguments) throw "arguments object is not defined";
				if(arguments[0] != 3) throw "wrong argument, expected 3, but: " + arguments[0];
				if(arguments.length != 2) throw "wrong argument count, expected 2, but: " + arguments.length;
				func.call("THIS0", a, b);
				func.apply("THIS1", [a, b]);
				func.apply("THIS2", [arguments[0], arguments[1]]);
				func.apply("THIS3", arguments);
				func.apply("THIS4", Array(arguments));
			}
			check(3, 5);
		}
		
		(function(a, b){
			if(a != 3) throw "wrong argument, expected 3, but: " + a;
			if(b != 5) throw "wrong argument, expected 5, but: " + b;
			if(!arguments) throw "arguments object is not defined";
			if(arguments[0] != 3) throw "wrong argument, expected 3, but: " + arguments[0];
			if(arguments.length != 2) throw "wrong argument count, expected 2, but: " + arguments.length;
			func.call([], a, b);
			func.apply([], [a, b]);
			func.apply([], arguments);
		})(3, 5);
		
		(function(){
			if(!arguments) throw "arguments object is not defined";
			if(arguments[0] != 3) throw "wrong argument, expected 3, but: " + arguments[0];
			if(arguments.length != 2) throw "wrong argument count, expected 2, but: " + arguments.length;
			func.call([], arguments[0], arguments[1]);
			func.apply([], arguments);
		})(3, 5);
	}
	{
		var func = function(arguments){
			if(arguments != 3){
				throw "wrong argument, expected 3, but: " + arguments;
			}
		};
		
		func(3, 5);
		func.call(null, 3, 5);
		func.apply(null, [3, 5]);
		func.call({}, 3, 5);
		func.apply({}, [3, 5]);
	}
}
out.println("done.");

out.print("Function constructor...");
{
	var fn = new Function("return 13;");
	var result;
	
	result = fn();
	if(result !== 13) throw "Expected to be 13, but: " + result;
	result = fn(7);
	if(result !== 13) throw "Expected to be 13, but: " + result;
	result = fn('asd');
	if(result !== 13) throw "Expected to be 13, but: " + result;
}
{
	var fn = new Function("a", "return a + a");
	var result;
	
	result = fn(1);
	if(result !== 2) throw "Expected to be 2, but: " + result;
	result = fn(2);
	if(result !== 4) throw "Expected to be 4, but: " + result;
	result = fn('d');
	if(result !== 'dd') throw "Expected to be 'dd', but: " + result;
}
{
	var fn = new Function("a", "b", "return a + b");
	var result;
	
	result = fn(0,2);
	if(result != 2) throw "Expected to be 2, but: " + result;
	result = fn(3,2);
	if(result != 5) throw "Expected to be 5, but: " + result;
	result = fn('a',2);
	if(result != 'a2') throw "Expected to be 'a2', but: " + result;
}
out.println("done.");

out.print("for test, var declaration in loop specification");
{
	/**
	 * no internal vars
	 */
	{
		if(index !== undefined){
			throw "index should be undefined!";
		}
		{
			for(var index = 0; index < 5; index++){
				if(index >= 5){
					throw "too much";
				}
			}
			if(index !== 5){
				throw "index must be 5, but: " + index;
			}
		}
		if(index !== undefined){
			throw "index should be undefined!";
		}
	}
	/**
	 * two vars, no internal vars
	 */
	{
		if(index !== undefined){
			throw "index should be undefined!";
		}
		if(yandex !== undefined){
			throw "yandex should be undefined!";
		}
		{
			for(var index = 0, yandex = 1; index < 5; index++, yandex++){
				if(index >= 5){
					throw "too much";
				}
			}
			if(index !== 5){
				throw "index must be 5, but: " + index;
			}
			if(yandex !== 6){
				throw "yandex must be 6, but: " + yandex;
			}
		}
		if(index !== undefined){
			throw "index should be undefined!";
		}
		if(yandex !== undefined){
			throw "yandex should be undefined!";
		}
	}
	/**
	 * with internal vars
	 */
	{
		if(index !== undefined){
			throw "index should be undefined!";
		}
		{
			for(var index = 0; index < 5; index++){
				var boolean = index >= 5;
				if(boolean){
					throw "too much";
				}
			}
			if(index !== 5){
				throw "index must be 5, but: " + index;
			}
		}
		if(index !== undefined){
			throw "index should be undefined!";
		}
	}
	/**
	 * two vars with internal vars
	 */
	{
		if(index !== undefined){
			throw "index should be undefined!";
		}
		if(yandex !== undefined){
			throw "yandex should be undefined!";
		}
		{
			for(var index = 0, yandex = 1; index < 5; index++, yandex++){
				var boolean = index >= 5;
				if(boolean){
					throw "too much";
				}
			}
			if(index !== 5){
				throw "index must be 5, but: " + index;
			}
			if(yandex !== 6){
				throw "yandex must be 6, but: " + yandex;
			}
		}
		if(index !== undefined){
			throw "index should be undefined!";
		}
		if(yandex !== undefined){
			throw "yandex should be undefined!";
		}
	}
}
{
	var array = [0,1,2,3,4,5];
	/**
	 * no internal vars
	 */
	{
		if(index !== undefined){
			throw "index should be undefined!";
		}
		{
			for(var index in array){
				if(index > 5){
					throw "too much";
				}
			}
			if(index !== 5){
				throw "index must be 5, but: " + index;
			}
		}
		if(index !== undefined){
			throw "index should be undefined!";
		}
	}
	/**
	 * with internal vars
	 */
	{
		if(index !== undefined){
			throw "index should be undefined!";
		}
		{
			for(var index in array){
				var boolean = index > 5;
				if(boolean){
					throw "too much";
				}
			}
			if(index !== 5){
				throw "index must be 5, but: " + index;
			}
		}
		if(index !== undefined){
			throw "index should be undefined!";
		}
	}
}
out.println("done.");

out.print("Create.map maps...");
{
	{
		var lookup = {};
		if(!lookup){
			throw "Cannot create inline map!";
		}
		lookup = {}; // intentionally here - check same think with no var (compiler)!
		if(!lookup){
			throw "Cannot create inline map with no 'var'!";
		}
		lookup.aa = 55;
		lookup.bb = 66;
		if(lookup.bb - lookup.aa != 11){
			throw "Property failed!";
		}
	}
	{
		var lookup = { bb : 66 };
		if(!lookup){
			throw "Cannot create inline map!";
		}
		lookup.aa = 55;
		if(lookup.bb - lookup.aa != 11){
			throw "Property failed!";
		}
	}
	{
		var lookup = {
			VlapanAPI : function(){
				this.zzz = 35;
				this.xxx = this.method1(arg1);
			},
			field1 : 55,
			field2 : {
				a : 'a',
				b : 'b'
			},
			method1 : function(arg1){
				return 'aa';
			}
		};
		if(!lookup){
			throw "Cannot create inline map!";
		}
		if(lookup.field1 != 55){
			throw "Property failed #1!";
		}
		if(lookup.field2.b != 'b'){
			throw "Property failed #2!";
		}
		lookup.aa = 57;
		if(lookup.aa != 57){
			throw "Property failed #3!";
		}
	}
	{
		var c1 = { description : "false" };
		var cKeys = Object.keys(c1).join(", "); // to see keys in error message, if any
		var c11 = c1.description;
		if(c11 != "false"){
			throw "c11=" + c11;
		}
	}
	{
		var c2 = { description : "false", hidden : "true" };
		var cKeys = Object.keys(c2).join(", "); // to see keys in error message, if any
		var c21 = c2.description;
		var c22 = c2.hidden;
		if(c21 != "false" || c22 != "true"){
			throw "c21=" + c21 + ", c22=" + c22;
		}
	}
	{
		var c3 = { description : "false", hidden : "true", file : "undefined" };
		var cKeys = Object.keys(c3).join(", "); // to see keys in error message, if any
		var c31 = c3.description;
		var c32 = c3.hidden;
		var c33 = c3.file;
		if(c31 != "false" || c32 != "true" || c33 != "undefined"){
			throw "c31=" + c31 + ", c32=" + c32 + ", c33=" + c33;
		}
	}
	{
		var cA = { description : "false", hidden : "true", file : "undefined", z : 1, y : 2, x : 3, a : 4, b : 5, c : 6, d : 7 };
		var cKeys = Object.keys(cA).join(", "); // to see keys in error message, if any
		var cA1 = cA.description;
		var cA2 = cA.hidden;
		var cA3 = cA.file;
		if(cA1 != "false" || cA2 != "true" || cA3 != "undefined" || cA.z != 1 || cA.y != 2 || cA.x != 3 || cA.a != 4 || cA.b != 5 || cA.c != 6 || cA.d != 7){
			throw "cA1=" + cA1 + ", cA2=" + cA2 + ", cA3=" + cA3;
		}
	}
	{
		var m = { 
			toString : function(){
				return "Hello!";
			} 
		};
		var t = m.toString();
		if( ("a" + m) != "a" + t){
			throw "'a" + t + "' expected, but: " + ("a" + m);
		}
	}
	{
		var m = { 
			valueOf : function(){
				return "World!";
			} 
		};
		var t = m.valueOf();
		if( ("a" + m) != "a" + t){
			throw "'a" + t + "' expected, but: " + ("a" + m);
		}
	}
	{
		var m = {};
		var t = m.toString();
		if( ("a" + m) != "a" + t){
			throw "'a" + t + "' expected, but: " + ("a" + m);
		}
	}
	{
		/**
		 * extra things
		 */
		var maps = [new Object(), {}];
		for(var map in maps){
			if(map.size()){
				var keys, j = 0;
				$output(keys){
					for(var i in map){
						(j++) && (=',');
						= i;
					}
				}
				throw "Incorrect, keyset: " + map.size() + ", keys=" + keys + ", check=" + map[keys];
			}
			if(map.size() > 0){
				throw "Incorrect, keyset: " + map.size();
			}
		}
		var maps = [{m : 5}, {"m" : 5}];
		for(var map in maps){
			if(map.size() != 1){
				throw "Incorrect, size=" + map.size();
			}
			if(map.m != 5){
				throw "Incorrect, value=" + map.m;
			}
		}
		var map = {m : 6, k : 7};
		if(map.size() != 2){
			throw "Incorrect, size=" + map.size();
		}
	}
	{
		// expression parser
		var images, thisImages;
		images = thisImages || {};
		images = thisImages || { a : 5 };
		images = (Math.round(0.1) && this.images) || {};
		if( imagesVersion == 5 ) { // should not go here
			change = this.createChange();
			change.images = null;
			change.imagesVersion = 5;
			images = {};
		}else{
			images = (Math.round(0.01) && this.images) || {};
		}
	}
}
{
	var value = {
		item : {
			getTitle : String
		},
		referer : 'refr',
		qnt : 1
	};
	var row = {
		data : {
			key : key,
			index : index + '.',
			image : {
				type : 'image',
				entry : value.item,
				link : value.referer || null
			},
			title : {
				data : value.item.getTitle(),
				subtext : [( 'àðò.: ' + value.item.codeNumber )],
				link : value.referer || null
			},
			qnt : Math.round(value.qnt) + 'øò.',
			price : Math.max(price, 2) + 'ð.',
			total : {
				data : Math.max(price * qnt, 2) + 'ð.',
				subtext : discountCost > 0 && [('ñêèäêà: ' + Math.max(discountCost, 2) + 'ð.')] || null
			}
		}
	};

	if(!row.data.title.subtext[0]){
		throw "";
	}
}
out.println("done.");

out.print("Core functions...");
{
	if( !isFinite ){
		throw "isFinite should be defined!";
	}
	if( !isFinite(1) ){
		throw "isFinite fail";
	}
	if( !isFinite(0) ){
		throw "isFinite fail";
	}
	if( !isFinite(null) ){
		throw "isFinite fail";
	}
	if( !isFinite("10") ){
		throw "isFinite fail";
	}
	if( isFinite(undefined) ){
		throw "isFinite fail";
	}
	if( isFinite(NaN) ){
		throw "isFinite fail";
	}
	if( isFinite(Infinity) ){
		throw "isFinite fail";
	}
	if( isFinite(+Infinity) ){
		throw "isFinite fail: " + (+Infinity);
	}
	if( isFinite(-Infinity) ){
		throw "isFinite fail: " + (-Infinity);
	}
	{
		var x = Infinity;
		if( isFinite(x) ){
			throw "isFinite fail: " + (x);
		}
		if( isFinite(-x) ){
			throw "isFinite fail: " + (-x);
		}
	}
	if( !isNaN ){
		throw "isNaN should be defined!";
	}
	if( isNaN(1) ){
		throw "isNaN fail";
	}
	if( isNaN(true) ){
		throw "isNaN fail";
	}
	if( isNaN(false) ){
		throw "isNaN fail";
	}
	if( isNaN("12") ){
		throw "isNaN fail";
	}
	if( !isNaN(undefined) ){
		throw "isNaN fail";
	}
	if( !isNaN(NaN) ){
		throw "isNaN fail";
	}
	if( !isNaN("string") ){
		throw "isNaN fail";
	}
	if( isNaN(null) ){
		throw "isNaN fail";
	}
	if( !parseFloat ){
		throw "parseInt should be defined!";
	}
	if( parseFloat("5") != 5 ){
		throw "parseInt fail";
	}
	if( parseFloat(5) != 5 ){
		throw "parseInt fail";
	}
	if( !parseInt ){
		throw "parseInt should be defined!";
	}
	if( parseInt("5") != 5 ){
		throw "parseInt fail";
	}
	if( parseInt("5", 16) != 5 ){
		throw "parseInt fail";
	}
	if( parseInt("10", 9) != 9 ){
		throw "parseInt fail";
	}
	if( parseInt(5) != 5 ){
		throw "parseInt fail";
	}
}
out.println("done.");

out.print("Operation2X SACCESS...");
{
	{
		var a = 1, b = 2;
		a += b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
	}
	{
		var a = 5, b = 2;
		a -= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
	}
	{
		var a = 1.5, b = 2;
		a *= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
	}
	{
		var a = 6, b = 2;
		a /= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
	}
	{
		var a = 7, b = 4;
		a %= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
	}
	{
		var a = 7, b = 3;
		a &= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
	}
	{
		var a = 7, b = 3;
		a |= b;
		if(a != 7){
			throw "Must be 7, but: " + a;
		}
	}
	{
		var a = 5, b = 3;
		a ^= b;
		if(a != 6){
			throw "Must be 6, but: " + a;
		}
	}
	{
		var a = 3, b = 1;
		a <<= b;
		if(a != 6){
			throw "Must be 6, but: " + a;
		}
	}
	{
		var a = 6, b = 1;
		a >>= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
		/**
		 * check shift
		 */
		a = (-2 >> 1);
		if(a != -1){
			throw "Must be -1, but: " + a;
		}
	}
	{
		var a = 6, b = 1;
		a >>>= b;
		if(a != 3){
			throw "Must be 3, but: " + a;
		}
		/**
		 * check shift
		 */
		a = (-2 >>> 1);
		if(a == -1){
			throw "Must not be -1, but: " + a;
		}
	}
}
out.println("done.");

out.print("Constructors...");
{
	{
		var x = new Object();
		if(!x){
			throw "Constructor returned null or undefined object!";
		}
	}
	{
		var boolean = new Boolean(42);
		if(boolean != true){
			throw "Boolean constructor is broken, result: " + boolean;
		}
	}
	{
		var boolean = Boolean(42);
		if(boolean != true){
			throw "Boolean converter is broken, result: " + boolean;
		}
		if(typeof boolean != 'boolean'){
			throw "Boolean converter is broken, result: " + boolean + ", typeof: " + (typeof boolean);
		}
		if(boolean.toString() !== 'true'){
			throw "Boolean converter is broken, result: " + boolean + ", toString: " + (boolean.toString());
		}
	}
	{
		var number = new Number(42);
		if(number != 42){
			throw "Number constructor is broken, result: " + number;
		}
	}
	{
		var number = new Number("42");
		if(number != 42){
			throw "Number constructor is broken, result: " + number;
		}
	}
	{
		var number = Number(42);
		if(number != 42){
			throw "Number converter is broken, result: " + number;
		}
		if(typeof number != 'number'){
			throw "Number converter is broken, result: " + number + ", typeof: " + (typeof number);
		}
		if(number.toString() !== '42'){
			throw "Number converter is broken, result: " + number + ", toString: " + (number.toString());
		}
	}
	{
		var number = Number("42");
		if(number != 42){
			throw "Number converter is broken, result: " + number;
		}
		if(typeof number != 'number'){
			throw "Number converter is broken, result: " + number + ", typeof: " + (typeof number);
		}
		if(number.toString() !== '42'){
			throw "Number converter is broken, result: " + number + ", toString: " + (number.toString());
		}
	}
	{
		var string = new String(42);
		if(string != '42'){
			throw "String constructor is broken, result: " + string;
		}
		if(typeof string != 'object'){
			throw "String constructor is broken, result: " + string + ", typeof: " + (typeof string);
		}
		var number = Number(string);
		if(number != 42){
			throw "Number converter is broken, result: " + number;
		}
		if(typeof number != 'number'){
			throw "Number converter is broken, result: " + number + ", typeof: " + (typeof number);
		}
		if(number.toString() !== '42'){
			throw "Number converter is broken, result: " + number + ", toString: " + (number.toString());
		}
	}
	{
		var string = String(42);
		if(string != '42'){
			throw "String converter is broken, result: " + string;
		}
		if(typeof string != 'string'){
			throw "String converter is broken, result: " + string + ", typeof: " + (typeof string);
		}
		var number = Number(string);
		if(number != 42){
			throw "Number converter is broken, result: " + number;
		}
		if(typeof number != 'number'){
			throw "Number converter is broken, result: " + number + ", typeof: " + (typeof number);
		}
		if(number.toString() !== '42'){
			throw "Number converter is broken, result: " + number + ", toString: " + (number.toString());
		}
	}
	{
		function obj(){
			this.aa = 5;
			return this;
		}
		obj.prototype = {
			bb : 6
		};
		var test = new obj();
		if(test.aa != 5){
			throw "'aa' field expected to be 5, but: " + test.aa;
		}
		if(test.bb != 6){
			throw "'bb' field expected to be 6, but: " + test.bb;
		}
	}
	{
		/**
		 * same with no explicit return
		 */
		function obj(){
			this.aa = 5;
		}
		obj.prototype = {
			bb : 6
		};
		var test = new obj();
		if(test.aa != 5){
			throw "'aa' field expected to be 5, but: " + test.aa;
		}
		if(test.bb != 6){
			throw "'bb' field expected to be 6, but: " + test.bb;
		}
	}
	{
		/**
		 * same with no explicit return and no explicit prototype
		 */
		function obj(){
			this.aa = 5;
		}
		obj.prototype.bb = 6;
		var test = new obj();
		if(test.aa != 5){
			throw "'aa' field expected to be 5, but: " + test.aa;
		}
		if(test.bb != 6){
			throw "'bb' field expected to be 6, but: " + test.bb;
		}
	}
	{
		/**
		 * Reflection
		 */
		import java.lang.Double;
		var x = Double.valueOf(5);
		if(!x){
			throw "Constructor returned null or undefined object!";
		}
	}
}
out.println("done.");

out.print("BOR / BAND general...");
{
	{
		var x = 2, y = 76;
		var r = (function(){
			return x || y;
		})();
		if(r !== 2){
			throw "BOR: expected: 2, result: " + r;
		}
	}
	{
		var x = false, y = 67;
		var r = (function(){
			return x || y;
		})();
		if(r !== 67){
			throw "BOR: expected: 67, result: " + r;
		}
	}
	{
		var x = false, y = 34;
		var r = (function(t){
			$output(t){
				= x || y;
			}
			return +t;
		})();
		if(r !== 34){
			throw "BOR: expected: 34, result: " + r;
		}
	}
	{
		var x = 5, y = 6;
		var r = (function(){
			return x && y;
		})();
		if(r !== 6){
			throw "BAND: expected: 6, result: " + r;
		}
	}
	{
		var x = '', y = 6;
		var r = (function(){
			return x && y;
		})();
		if(r !== ''){
			throw "BAND: expected: '', result: " + r;
		}
	}
	{
		var x = 0, y = 6;
		var r = (function(t){
			$output(t){
				= x && y;
			}
			return +t;
		})();
		if(r !== 0){
			throw "BAND: expected: 0, result: " + r;
		}
	}
	{
		var x = 3, y = 0;
		var r = (function(){
			return !!x || y;
		})();
		if(r !== true){
			throw "BOR: 1: expected: true, result: " + r;
		}
		var r = (function(){
			return !!x || (y*2);
		})();
		if(r !== true){
			throw "BOR: 2: expected: true, result: " + r;
		}
	}
	{
		var x = 1, y = 0;
		var r = (function(){
			return !x || y;
		})();
		if(r !== 0){
			throw "BOR: expected: 0, result: " + r;
		}
	}
	{
		var x = 1, y = 0;
		var r = (function(){
			return !y && !x && y;
		})();
		if(r !== false){
			throw "BAND: 1: expected: false, result: " + r;
		}
		var r = (function(){
			return !x && y;
		})();
		if(r !== false){
			throw "BAND: 2: expected: false, result: " + r;
		}
	}
	{
		var x = undefined, y = 0;
		var r = (function(){
			return !x || y;
		})();
		if(r !== true){
			throw "BOR: expected: true, result: " + r;
		}
	}
	{
		var x = undefined, y = 0;
		var r = (function(){
			return !x && y;
		})();
		if(r !== 0){
			throw "BAND: expected: 0, result: " + r;
		}
	}
	{
		var x = "", y = 0;
		var r = (function(){
			return !x.toString() || y;
		})();
		if(r !== true){
			throw "BOR: expected: true, result: " + r;
		}
	}
	{
		var x = "", y = undefined;
		var r = (function(){
			return !x.toString() && y;
		})();
		if(r !== undefined){
			throw "BAND: expected: undefined, result: " + r;
		}
	}
	{
		var x = "", y = 7;
		var r = (function(){
			return !x.toString() && y;
		})();
		if(r !== 7){
			throw "BAND: expected: 7, result: " + r;
		}
	}
}
out.println("done.");

out.print("BOR / BAND extended with maps...");
{
	var change = { description : false, hidden : true };
	if((change.description || change.hidden || flag) && recFile){
		throw "Incorrect IF flow!";
	}
}
{
	var change = { description : true, hidden : false };
	if((change.description || change.hidden || flag) && recFile){
		throw "Incorrect IF flow!";
	}
}
{
	var change = { description : false, hidden : false };
	var flag = true;
	if((change.description || change.hidden || flag) && recFile){
		throw "Incorrect IF flow!";
	}
}
{
	var recFile = true;
	var change = { description : false, hidden : true };
	if((change.description || change.hidden || flag) && recFile){
		// ignore
	}else{
		throw "Incorrect IF flow!";
	}
}
{
	var recFile = true;
	var change = { description : true, hidden : false };
	if((change.description || change.hidden || flag) && recFile){
		// ignore
	}else{
		throw "Incorrect IF flow!";
	}
}
{
	var recFile = true;
	var change = { description : false, hidden : false };
	var flag = true;
	if((change.description || change.hidden || flag) && recFile){
		// ignore
	}else{
		throw "Incorrect IF flow!";
	}
}
{
	var a = {};
	if(a.aaa != undefined){
		throw "Should be undefined (1), but: " +  a.aaa;
	}
	if(a.aaa !== undefined){
		throw "Should be exactly undefined (1), but: " +  a.aaa;
	}
	a.aaa += 2;
	if(!isNaN(a.aaa)){
		throw "Should be NaN (1), but: " +  a.aaa;
	}
}
{
	var a = new Object();
	if(!a){
		throw "Undefined object on 'new Object()'";
	}
	if(a.aaa != undefined){
		throw "Should be undefined (2), but: " +  a.aaa;
	}
	a.aaa += 2;
	if(!isNaN(a.aaa)){
		throw "Should be NaN (2), but: " +  a.aaa;
	}
}
out.println("done.");

out.print("DATA TO CONST compare...");
{
	var a = 5;
	if(!a){
		throw;
	}
	if(a < 5){
		throw;
	}
	if(a > 5){
		throw;
	}
	if(a != 5){
		throw;
	}
	if(!(a == 5)){
		throw;
	}
	if(!(a === 5)){
		throw;
	}
	if(a === 6){
		throw;
	}
	if(!(a !== 6)){
		throw;
	}
	if(a - 5 != 0){
		throw;
	}
	if(a - 0 != 5){
		throw;
	}
	if(a + 0 != 5){
		throw;
	}
	if(a - null != 5){
		throw;
	}
	if(a + null != 5){
		throw;
	}
}
out.println("done.");

out.print("Strict (exact) compare...");
{
	if(true === 1) {
		throw;
	}
	if(true !== !!1) {
		throw;
	}
	if(true === !!0) {
		throw;
	}
	if(!(true !== 1)) {
		throw;
	}
	if(false === 0) {
		throw;
	}
	if(false !== !!0) {
		throw;
	}
	if(false === !!1) {
		throw;
	}
	if(!(false !== 0)) {
		throw;
	}
	if(0 !== 0) {
		throw;
	}
	if(!(0 === 0)) {
		throw;
	}
	var z = 5;
	if(z === 6) {
		throw;
	}
	if(!(z !== 6)) {
		throw;
	}
	if(!("aa" === "aa")) {
		throw;
	}
	if("aa" !== "aa") {
		throw;
	}
	if(!("aa" !== "aab")) {
		throw;
	}
}
out.println("done.");

out.print("Unary test...");
{
	var value = "56";
	var a;
	a = value;
	if(a === 56){
		throw "Should not!";
	}
	a = +value;
	if(a !== 56){
		throw "Should be number 56, but: " + a;
	}
	a = typeof a;
	if(a != 'number'){
		throw "Should be number, but: " + a;
	}
}
{
	var value = 56;
	var a;
	
	a = -value;
	if(a != -56){
		throw "Must be -56, but: " + a;
	}
	a = !value;
	if(a !== false){
		throw "Must be false, but: " + a;
	}
	a = (!(!value));
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = (!!value);
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = !!value;
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = !!!(value + 2);
	if(a !== false){
		throw "Must be false, but: " + a;
	}
	a = !!!value;
	if(a !== false){
		throw "Must be false, but: " + a;
	}
	a = !!(!!value);
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = !!!!value;
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = +value;
	if(a != 56){
		throw "Must be 56, but: " + a;
	}
	var object = { value : 57 };
	a = -object.value;
	if(a != -57){
		throw "Must be -57, but: " + a;
	}
	a = +object.value;
	if(a != 57){
		throw "Must be 57, but: " + a;
	}
	a = !object.value;
	if(a !== false){
		throw "Must be false, but: " + a;
	}
	a = !object.value2;
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = !!object.value;
	if(a !== true){
		throw "Must be true, but: " + a;
	}
	a = !!object.value2;
	if(a !== false){
		throw "Must be false, but: " + a;
	}
}
out.println("done.");

out.print("Logical OR test...");
{
	if((0 || 5) !== 5) {
		throw "(0 || 5) should evaluate to 5, got: " + (0 || 5);
	}
	if((0 || 5 || 6 || 7) !== 5) {
		throw "(0 || 5 || 6 || 7) should evaluate to 5, got: " + (0 || 5 || 6 || 7);
	}
	if((1 || 5) !== 1) {
		throw "(1 || 5) should evaluate to 1, got: " + (1 || 5);
	}
	if((1 || 5 || 6 || 7) !== 1) {
		throw "(1 || 5 || 6 || 7) should evaluate to 1, got: " + (1 || 5 || 6 || 7);
	}
	if((0 || 0) !== 0) {
		throw "(0 || 0) should evaluate to 0, got: " + (0 || 0);
	}
	if((0 || false) !== false) {
		throw "(0 || false) should evaluate to false, got: " + (0 || false);
	}
}
{
	var c0 = 0, c1 = 1, c5 = 5, c6 = 6, c7 = 7, cfalse = false;
	if((c0 || c5) !== 5) {
		throw "(c0 || c5) should evaluate to 5, got: " + (c0 || c5);
	}
	if((c0 || c5) !== c5) {
		throw "(c0 || c5) should evaluate to c5, got: " + (c0 || c5);
	}
	if((c0 || c5 || c6 || c7) !== 5) {
		throw "(c0 || c5 || c6 || c7) should evaluate to 5, got: " + (c0 || c5 || c6 || c7);
	}
	if((c0 || c5 || c6 || c7) !== c5) {
		throw "(c0 || c5 || c6 || c7) should evaluate to c5, got: " + (c0 || c5 || c6 || c7);
	}
	if((c1 || c5) !== 1) {
		throw "(c1 || c5) should evaluate to 1, got: " + (c1 || c5);
	}
	if((c1 || c5) !== c1) {
		throw "(c1 || c5) should evaluate to c1, got: " + (c1 || c5);
	}
	if((c1 || c5 || c6 || c7) !== 1) {
		throw "(c1 || c5 || c6 || c7) should evaluate to 1, got: " + (c1 || c5 || c6 || c7);
	}
	if((c1 || c5 || c6 || c7) !== c1) {
		throw "(c1 || c5 || c6 || c7) should evaluate to c1, got: " + (c1 || c5 || c6 || c7);
	}
	if((c0 || c0) !== 0) {
		throw "(c0 || c0) should evaluate to 0, got: " + (c0 || c0);
	}
	if((c0 || c0) !== c0) {
		throw "(c0 || c0) should evaluate to c0, got: " + (c0 || c0);
	}
	if((c0 || false) !== false) {
		throw "(c0 || false) should evaluate to false, got: " + (c0 || false);
	}
	if((c0 || cfalse) !== false) {
		throw "(c0 || cfalse) should evaluate to false, got: " + (c0 || cfalse);
	}
	if((c0 || cfalse) !== cfalse) {
		throw "(c0 || cfalse) should evaluate to cfalse, got: " + (c0 || cfalse);
	}
}
{
	try{
		/**
		 * throw now is not a part of expression
		 */
		//true || throw "error";
	}catch(e){
		throw "throw should be unreacheable due to || flow";
	}
}
out.println("done.");

out.print("Logical AND test...");
{
	if((0 && 5)) {
		throw "(0 && 5) should evaluate to false, got: " + (0 && 5);
	}
	if((1 && 5) !== 5) {
		throw "(1 && 5) should evaluate to 5, got: " + (1 && 5);
	}
	if((1 && 5 && 6 && 7) !== 7) {
		throw "(1 && 5 && 6 && 7) should evaluate to 7, got: " + (1 && 5 && 6 && 7);
	}
	if((1 && true) !== true) {
		throw "(1 && true) should evaluate to true, got: " + (1 && true);
	}
	if((1 && false) !== false) {
		throw "(1 && false) should evaluate to false, got: " + (1 && false);
	}
	if((1 && 0)) {
		throw "(1 && 0) should evaluate to false, got: " + (1 && 0);
	}
	if((1 && 0 && 6)) {
		throw "(1 && 0 && 6) should evaluate to false, got: " + (1 && 0 && 6);
	}
	try{
		false && test000();
	}catch(e){
		throw "throw should be unreacheable due to && flow";
	}
}
{
	var c0 = 0, c1 = 1, c5 = 5, c6 = 6, c7 = 7, ctrue = true, cfalse = false;
	if((c0 && c5)) {
		throw "(c0 && c5) should evaluate to false, got: " + (c0 && c5);
	}
	if((c1 && c5) !== 5) {
		throw "(c1 && c5) should evaluate to 5, got: " + (c1 && c5);
	}
	if((c1 && c5) !== c5) {
		throw "(c1 && c5) should evaluate to c5, got: " + (c1 && c5);
	}
	if((c1 && c5 && c6 && c7) !== 7) {
		throw "(c1 && c5 && c6 && c7) should evaluate to 7, got: " + (c1 && c5 && c6 && c7);
	}
	if((c1 && c5 && c6 && c7) !== c7) {
		throw "(c1 && c5 && c6 && c7) should evaluate to c7, got: " + (c1 && c5 && c6 && c7);
	}
	if((c1 && true) !== true) {
		throw "(c1 && true) should evaluate to true, got: " + (c1 && true);
	}
	if((c1 && ctrue) !== true) {
		throw "(c1 && ctrue) should evaluate to true, got: " + (c1 && ctrue);
	}
	if((c1 && ctrue) !== ctrue) {
		throw "(c1 && ctrue) should evaluate to ctrue, got: " + (c1 && ctrue);
	}
	if((c1 && false) !== false) {
		throw "(c1 && false) should evaluate to false, got: " + (c1 && false);
	}
	if((c1 && cfalse) !== false) {
		throw "(c1 && cfalse) should evaluate to false, got: " + (c1 && cfalse);
	}
	if((c1 && cfalse) !== cfalse) {
		throw "(c1 && cfalse) should evaluate to cfalse, got: " + (c1 && cfalse);
	}
	if((c1 && c0)) {
		throw "(c1 && c0) should evaluate to false, got: " + (c1 && c0);
	}
	if((c1 && c0 && c6)) {
		throw "(c1 && c0 && c6) should evaluate to false, got: " + (c1 && c0 && c6);
	}
	try{
		false && test000();
	}catch(e){
		throw "throw should be unreacheable due to && flow";
	}
}
out.println("done.");

out.print("bitwise test...");
{
	var a, b;
	a = 5;
	b = 3;
	if((a & b) != 1){
		throw "Invalid bitwise AND: " + (a & b);
	}
	a = 4025962439;
	b = 0xffffffff;
	if((a & b) != -269004857){
		throw "Invalid bitwise AND: " + (a & b);
	}
	a = 1;
	b = 2;
	if((a | b) != 3){
		throw "Invalid bitwise OR: " + (a | b);
	}
	a = 1;
	b = 3;
	if((a ^ b) != 2){
		throw "Invalid bitwise XOR: " + (a ^ b);
	}
	a = 2;
	b = 1;
	if((a << b) != 4){
		throw "Invalid shift left: " + (a << b);
	}
	if((a >> b) != 1){
		throw "Invalid shift right: " + (a >> b);
	}
	if((a >>> b) != 1){
		throw "Invalid unsigned shift right: " + (a >>> b);
	}
	a = -1;
	if(~a != 0){
		throw "Bitwise NOT -1 is not zero, but: " + (~a);
	}
}
out.println("done.");

out.print("big integers test...");
{
	var a, b = -1, z = 0;
	a = 4025962439;
	if((a | z) != -269004857){
		throw "Invalid bitwise OR: " + (a | z);
	}
	if((a & b) != -269004857){
		throw "Invalid bitwise AND: " + (a & b);
	}
	if((a + z) != 4025962439){
		throw "Invalid ADD: " + (a + z);
	}
	var c = a + z;
	if((c & b) != -269004857){
		throw "Invalid bitwise AND: " + (c & b);
	}
	if(((a + z) & b) != -269004857){
		throw "Invalid bitwise AND: " + ((a + z) & b);
	}
	if((a+z & b-z) != -269004857){
		throw "Invalid bitwise AND: " + (a+z & b-z);
	}
}
out.println("done.");

out.print("new java object test...");
{
	if(Double){
		throw "Not yet, 'Double' should not be defined, but: " + Double;
	}
	import java.lang.Double;
	if(!Double){
		throw "Must be defined!";
	}
	var a;
	if(a){
		throw "Not yet!";
	}
	a = Double.valueOf(5.5);
	if(!a){
		throw "Must be defined!";
	}
	if(a.a){
		throw "No property should be defined!";
	}
	if(a != 5.5){
		throw "Should be equal to 5.5";
	}
}
out.println("done.");

out.print("OBJECT basic check tests...");
{
	if(!Object){
		throw "no global Object object accessible!";
	}
	if(!Object.prototype){
		throw "no Object.prototype accessible!";
	}
	if(!(new Object())){
		throw "(new Object()) is not defined!";
	}
}
out.println("done.");

out.print("OBJECT property enumerability...");
{
	for(var j = 1;j<30;++j){
		const o = {}, keysA = [], keysD = [], keysU = [];
		
		var added = 0;
		for(var i = 0;i<j;++i){
			var key = "prop" + j + "_" + i + "_" + !(i&1);
			Object.defineProperty(o, key + "|", {
				value : key,
				enumerable : !(i&1),
				writable : true,
				configurable : !(i&1)
			});
			keysA.push(key);
			++added;
		}

		const keys1 = Object.keys(o);
		const expt1 = Math.ceil(j/2);
		if(keys1.length != expt1){
			console.log("keysE: %s", keys1);
			console.log("keysA: %s: %s", added, keysA);
			throw "Object keys1 count is unexpected: j=" + j + ", count: " + keys1.length + ", expect: " + expt1;
		}
		
		{
			const keysString = keys1.join("");
			if(!keysString.includes("true|")){
				throw "Object keys1 do not include expected values: j=" + j + ", " + keysString;
			}
			if(keysString.includes("false|")){
				throw "Object keys1 do include unexpected values: j=" + j + ", " + keysString;
			}
		}		

		var deleted = 0;
		for(var i = 0;i<j;++i){
			if( (i&3) !== 0 ){
				var key = "prop" + j + "_" + i + "_" + !(i&1);
				delete o[key + "|"];
				keysD.push(key);
				++deleted;
			}
		}
		
		const keys2 = Object.keys(o);
		const expt2 = Math.ceil(j/4);
		if(keys2.length != expt2){
			console.log("keysE: %s", keys2);
			console.log("keysA: %s: %s", added, keysA);
			console.log("keysD: %s: %s", deleted, keysD);
			throw "Object keys2 count is unexpected: j=" + j + ", count: " + keys2.length + ", expect: " + expt2;
		}
		
		var updated = 0;
		for(var i = 0;i<j;++i){
			if( !(i&1) ){
				var key = "prop" + j + "_" + i + "_" + !(i&1);
				o[key + "|"] += "Z";
				keysU.push(key);
				++updated;
			}
		}
	
		const keys3 = Object.keys(o);
		const expt3 = Math.ceil(j/2);
		if(keys3.length != expt3){
			console.log("keysE: %s", keys1);
			console.log("keysA: %s: %s", added, keysA);
			console.log("keysD: %s: %s", deleted, keysD);
			console.log("keysU: %s: %s", updated, keysU);
			throw "Object keys1 count is unexpected: j=" + j + ", count: " + keys3.length + ", expect: " + expt3;
		}
	}
}
out.println("done.");

out.print("new Object() test...");
{
	var a;
	if(a){
		throw "Not yet!";
	}
	if(!Object){
		throw "'Object' must be defined in GLOBAL by default (and provide constructor)";
	}
	a = new Object();
	if(!a){
		throw "Must be defined!";
	}
	if(a.a){
		throw "Not yet!";
	}
	a.a = 5;
	if(!a.a){
		throw "Must be defined!";
	}
}
out.println("done.");

out.print("complicated assignment...");
{
	var o = new Object();
	var value = 5913621;
	o.v1 = o.v2 = o.v3 = value;
	if(value != o.v1 || o.v1 != o.v2 || o.v2 != o.v3){
		throw "Assignment is broken: value=" + value + ", v1=" + o.v1 + ", v2=" + o.v2 + ", v3=" + o.v3;
	}
}
{
	var o = new Object();
	var value = 5913621;
	o.v1 = o.v2 = o.v3 = {};
	o.v1.zz = o.v2.zz = o.v3.zz = value;
	if(value != o.v1.zz || o.v1.zz != o.v2.zz || o.v2.zz != o.v3.zz){
		throw "Assignment is broken: value=" + value + ", v1=" + o.v1.zz + ", v2=" + o.v2.zz + ", v3=" + o.v3.zz;
	}
}
out.println("done.");

out.print("output as function result...");
{
	{
		var s;
		$output(s){
			function a(){
				%>123<%
				%>456<%
			}
			a();
		}
		
		if("123456" != s){
			throw "output from function is broken: " + s;
		}
	}
	{
		//
		// Should return null or undefined 
		//
		
		function a(){
			%>123<%
			%>456<%
		}
		
		if(a()){
			throw "output as function result is broken: " + a();
		}
	}
}
out.println("done.");

out.print("output with complicated access...");
{
	var s = {
		z : [0,1,2,{ text1 : 'initial' }]
	};
	if("initial" != s.z[3].text1){
		throw "parser is broken, expected to be 'initial', but: " + s.z[3].text1;
	}
	function test(fieldName){
		$output(s.z[3][fieldName]){
			function a(){
				%>123<%
				%>456<%
			}
			a();
		}
		if("123456" != s.z[3][fieldName]){
			throw "output from function is broken fieldName="+fieldName+" , result=" + s.z[3].text1;
		}
	}
	test('test1');
	test('test2');
}
out.println("done.");


out.print("output in other output...");
{
	
	var x = '';
	
	function ob1(y){
		var x = '';
		$output(x){
			= 'AA';
			= y;
			= 'BB';
		}
		return x;
	}
	
	function ob2(y){
		return 'AA' + y + 'BB';
	}
	
	$output(x){
		= '123-';
		= ob1('zz');
		= ob2('xx');
		= '-321';
	}
	
	var c = "123-AAzzBBAAxxBB-321";
	
	if(c !== x){
		throw "nested output is broken, expected to be '" + c + "', but: '" + x + "'";
	}
}
out.println("done.");

out.print("inline constructor test...");
{
	var a;
	if(a){
		throw "Not yet!";
	}
	{
		function Test1(){
			this.aa = "aa";
			this.bb = "bb";
			return this;
		}
		Test1.prototype = new Object();
		Test1.prototype.cc = "cc";
		a = new Test1();
		if(!a){
			throw "constructor shouldn't return undefined value!";
		}
		if(a.constructor != Test1){
			throw "constructor property is not equal to constructor function used to create an object!";
		}
		if(a.aa != "aa"){
			throw "Must be defined (property)!";
		}
		if(a.bb != "bb"){
			throw "Must be defined (property)!";
		}
		if(a.cc != "cc"){
			throw "Must be defined (prototype)!";
		}
	}
	{
		// unsupported !!!
		function Test2(){
			this.aa = "aa";
			this.bb = "bb";
			// no 'return this' - should still return created object
		}
		a = new Test2();
		if(!a){
			throw "constructor shouldn't return undefined value!";
		}
		if(a.aa != "aa"){
			throw "Must be defined!";
		}
		if(a.bb != "bb"){
			throw "Must be defined!";
		}
		if(a.constructor != Test2){
			throw "constructor property is not equal to constructor function used to create an object!";
		}
	}
}
out.println("done.");

out.print("Logical AND test extended...");
{
	{
		var a = {};
		if(a.rz){
			throw "object properties error - should not exist!";
		}
		a.rz = 1;
		if(a.rz != 1){
			throw "object properties error - should be equal to one!";
		}
		if(a.rz !== 1){
			throw "object properties error - should be strictly equal to one!";
		}
		a.rz = 0;
		function fn(){
			if(!a){
				throw "a - should be defined!";
			}
			if(a.rz != 0){
				throw "a.rz property should be equal to zero, but: " + a.rz;
			}
			a.rz = 1;
			if(a.rz != 1){
				throw "object properties error - should be equal to one!";
			}
		}
		fn();
		if(a.rz != 1){
			throw "function call was not executed (a=" + a + ", a.rz=" + a.rz + ")!";
		}
		a.rz = 0;
		try{
			a.fn && a.fn();
		}catch(e){
			throw "&& flow function call should not be attempted to execute!";
		}
		a.fn = fn;
		a.fn && a.fn();
		if(a.rz !== 1){
			throw "&& flow function call was not executed!";
		}
	}
}
out.println("done.");

out.print("String concatenation test...");
{
	var aa = "aa";
	var test;
	
	test = aa + "bb";
	if(test != "aabb"){
		throw "String cancatenation test fail: '"+test+"'";
	}
	
	test = aa + null + "bb";
	if(test != "aanullbb"){
		throw "String cancatenation test fail: '"+test+"'";
	}
	
	test = aa + true + "bb";
	if(test != "aatruebb"){
		throw "String cancatenation test fail: '"+test+"'";
	}
	test = aa + 5;
	if(test != "aa5"){
		throw "String cancatenation test fail: '"+test+"'";
	}

	if( [].toString() != "" ){
		throw "Expected that empty list converted to string will be an empty string, but: " + [].toString();
	}
	
	
	if( ["aaa"].toString() != "aaa" ){
		throw "Expected that list converted to string will be 'aaa', but: " + ["aaa"].toString();
	}
	
	if( String([]) != "" ){
		throw "Expected that empty array converted to string by String function will be '', but: " +String([]);
	}
	
	if( String([ 1, 2 ]) != "1,2" ){
		throw "Expected that empty array converted to string by String function will be '1,2', but: " +String([ 1, 2 ]);
	}
	
	if( [] + "[]" != "[]" ){
		throw "Expected that [] concatenated with '[]' string will be '[]', but: " + ([] + "[]");
	}
}
out.println("done.");

out.print("SACCESS test...");
{
	{
		var imap = {};
		imap.type = "aaa";
		var objecttype = null;
		objecttype = imap.type && !objecttype ? imap.type : objecttype;
		if( objecttype != imap.type ){
			throw "Property read access is broken!";
		}
		imap[objecttype] = imap.type && !objecttype ? imap.type : objecttype;
		if( imap["aaa"] != "aaa" ){
			throw "Property write access is broken!";
		}
		imap[objecttype + 5] = imap.type && !objecttype ? imap.type : objecttype;
		imap[objecttype + "-" + objecttype] = imap.type && !objecttype ? imap.type : objecttype;
		if( imap.aaa5 != "aaa" || imap["aaa-aaa"] != "aaa" ){
			throw;
		}
	}
	{
		{
			var key = "aaa" + ["[]"];
			if(key != "aaa[]"){
				throw "key is not 'aaa[]' but: " + key;
			}
		}
		{
			var brr;
			(brr = {})["aaa" + ["[]"]] += 55;
			if(!isNaN(brr["aaa[]"])){
				throw 'brr["aaa[]"] should be NaN, but: ' + brr["aaa[]"];
			}
		}
		{
			var brr;
			(brr = {})["aaa" + ["[]"]] += 5 + ["[]"];
			if(brr["aaa[]"] != "undefined5[]"){
				throw 'brr["aaa[]"] should be "undefined5[]", but: ' + brr["aaa[]"];
			}
		}
	}
	{
		var arr = {};

		var cc = 0;
		arr.aabb = 0;
		arr.aabb += cc + 2;
		if(arr.aabb != 2){
			throw "Expected to be 2, but: " + arr.aabb;
		}
		
		arr.aabb = null;
		arr.aabb += cc + 2;
		if(arr.aabb != 2){
			throw "Expected to be 2, but: " + arr.aabb;
		}

		arr.aabb = null;
		var dd = 0;
		arr.aabb += cc + dd + 2;
		if(arr.aabb != 2){
			throw "Expected to be 2, but: " + arr.aabb;
		}

		arr.aabb = null;
		var dd = null;
		arr.aabb += cc + dd + 2;
		if(arr.aabb != 2){
			throw "Expected to be 2, but: " + arr.aabb;
		}
	}
}
out.println("done.");

out.print("Normal access & call, extra whitespace...");
{
	var a = {
		b : 771,
		c : function(){
			return 773;
		},
		d : function(){
			return { e : 777 };
		},
	};
	
	if( a.b !== 771 ) {
		throw "oops, normal access failed!";
	}
	if( a .b !== 771 ) {
		throw "whitespace #1 breaks access!";
	}
	if( a. b !== 771 ) {
		throw "whitespace #2 breaks access!";
	}
	if( a . b !== 771 ) {
		throw "whitespace #3 breaks access!";
	}
	if( a ["b"] !== 771 ) {
		throw "whitespace #4 breaks access!";
	}
	
	if( a.c() !== 773 ) {
		throw "oops, normal call failed!";
	}
	if( a .c() !== 773 ) {
		throw "whitespace #1 breaks call!";
	}
	if( a. c() !== 773 ) {
		throw "whitespace #2 breaks call!";
	}
	if( a . c() !== 773 ) {
		throw "whitespace #3 breaks call!";
	}
	if( a . c () !== 773 ) {
		throw "whitespace #4 breaks call!";
	}
	if( a ["c"] () !== 773 ) {
		throw "whitespace #5 breaks call!";
	}
	if( a.d().e !== 777 ) {
		throw "oops, normal call #2 failed!";
	}
	if( a.d(). e !== 777 ) {
		throw "whitespace #6 breaks call!";
	}
	if( a.d() . e !== 777 ) {
		throw "whitespace #7 breaks call!";
	}
	
}
out.println("done.");

out.print("TRINARY BASIC test...");
{
	var a = true;
	var b = 2;
	var c = 5;
	var x = a ? b : c;
	if( x != 2 ){
		throw "Should be equals to 2";
	}
}
{
	var a = false;
	var b = 2;
	var c = 5;
	var x = a ? b : c;
	if( x != 5 ){
		throw "Should be equals to 5";
	}
}
{
	var imap = 2;
	var type = Math.round(imap) == 1 ? 'list' : (Math.round(imap) == 2 ? 'map' : false);
	if(type != "map"){
		throw "Should be equals to 'map'";
	}
}
{
	var imap = 1;
	var type = Math.round(imap) == 1 ? 'list' : (Math.round(imap) == 2 ? 'map' : false);
	if(type != "list"){
		throw "Should be equals to 'list'";
	}
}
{
	var imap = 0;
	var type = Math.round(imap) == 1 ? 'list' : (Math.round(imap) == 2 ? 'map' : false);
	if(type != false){
		throw "Should be equals to FALSE";
	}
}
{
	// kinda more complex
	var data = {};
	var resultMaps = !data.resultMaps ? { trash : {}, old : {} } : data.resultMaps;
}
out.println("done.");

out.print("TRY CATCH FINALLY test...");
{
	var test;
	$output(test){
		var aa = 5;
		try{
			= "2 true";
		}catch(e){
			= e;
		}finally{
			= " 2finally";
		}
		try{
			= " 3true";
		}finally{
			= " 3finally";
		}
		try{
			throw "fake";
		}catch(e){
			= " error_substring";
		}finally{
			= " 4finally";
		}
		try{
			zzz.createChange();
		}catch(e){
			= " error_null_acall";
		}
	}
	if( test != "2 true 2finally 3true 3finally error_substring 4finally error_null_acall"){
		throw "Test value mismatch: " + test;
	}
}
out.println("done.");

out.print("THROW test...");
{
	try{
		throw "check1";
	}catch(error){
		if( error != "check1" ){
			throw new Error("error != check1");
		}
	}
	try{
		{
			var a = "check2";
			throw a;
		}
	}catch(error){
		if( error != "check2" ){
			throw "error != check2";
		}
	}
	try{
		for(var i = 0; i < 5; i++){
			var a = "check2";
			throw a;
		}
	}catch(error){
		if( error != "check2" ){
			throw "error != check2";
		}
	}
}
{
	var e = null;

	try{
		throw "TEST";
	}catch( error ){
		e = true;
	}
	
	if( !e ){
		throw "Must be TRUE";
	}

	try{
		var a = [];
		var b = [];
		var c = [];
	}catch( error ){
		throw "Must not throw!";
	}
}
out.println("done.");

out.print("CATCH test [simple]...");
{
	{
		{
			var a = true;
			try{
				// this line should produce error!
				new null();
			}catch(error){
				a = false;
			}
			if(a){
				throw "catch doesn't work!";
			}
		}
		{
			var a = true;
			try{
				// this line should produce error!
				null.a(1);
			}catch(error){
				a = false;
			}
			if(a){
				throw "catch doesn't work!";
			}
		}
	}
}
out.println("done.");



out.print("Inline function...");
{
	{
		function fn(){
			// ignore
		}
		var result = fn();
		if(result !== undefined){
			throw "empty return statement should return 'undefined', but: " + result;
		}
	}
	{
		function fn(){
			return;
		}
		var result = fn();
		if(result !== undefined){
			throw "empty return statement should return 'undefined', but: " + result;
		}
	}
	{
		function fn(){
			return undefined;
		}
		var result = fn();
		if(result !== undefined){
			throw "empty return statement should return 'undefined', but: " + result;
		}
	}
	{
		function fn(){
			return true;
		}
		var result = fn();
		if(result !== true){
			/**
			 * need to see it in error if shit happens
			 */
			resultExpect = true;
			throw (true) + " expected, got: " + result;
		}
	}
	{
		function fn(a){
			return a * 2;
		}
		var result = fn(2);
		if(result != 2 * 2){
			throw (2 * 2) + " expected, got: " + result;
		}
	}
	{
		function fn(a, b){
			return a + b;
		}
		var result = fn(2, 3);
		if(result != 2 + 3){
			throw (2 + 3) + " expected, got: " + result;
		}
	}
	/**
	 * .call
	 */
	{
		{
			function fn(a){
				return a * 2;
			}
			var result = fn.call(null, 2);
			if(result != 2 * 2){
				throw (2 * 2) + " expected, got: " + result;
			}
		}
		{
			function fn(a, b, c){
				return a * b * c * 2;
			}
			var result = fn.call(null, 2, 3, 4);
			if(result != 2 * 3 * 4 * 2){
				throw (2 * 3 * 4 * 2) + " expected, got: " + result;
			}
		}
		{
			function fn(a){
				return this * a;
			}
			var result = fn.call(2, 2);
			if(result != 2 * 2){
				throw (2 * 2) + " expected, got: " + result;
			}
		}
	}
	/**
	 * .apply
	 */
	{
		{
			function fn(a){
				return a * 2;
			}
			var result = fn.apply(null, [2]);
			if(result != 2 * 2){
				throw (2 * 2) + " expected, got: " + result;
			}
		}
		{
			function fn(a, b, c){
				return a * b * c * 2;
			}
			var result = fn.apply(null, [2, 3, 4]);
			if(result != 2 * 3 * 4 * 2){
				throw (2 * 3 * 4 * 2) + " expected, got: " + result;
			}
		}
		{
			function fn(a){
				return this * a;
			}
			var result = fn.apply(2, [2]);
			if(result != 2 * 2){
				throw (2 * 2) + " expected, got: " + result;
			}
		}
	}
}
out.println("done.");

out.print("Inline function context variable read write access...");
{
	var test1 = 'AB', test2;
	{
		function fn(){
			if(test1 != 'AB'){
				throw "'test1' expected to be equal to 'AB', but=" + test1;
			}
			test1 += 'CD';
			test2 = 'ABCD';
		}
		fn();
		if(test1 != 'ABCD'){
			throw "'test1' expected to be equal to 'ABCD', but=" + test1;
		}
		if(test2 != 'ABCD'){
			throw "'test2' expected to be equal to 'ABCD', but=" + test2;
		}
	}
}
out.println("done.");

out.print("CATCH test [function]...");
{
	{
		function fNormalCatch(){
			try{
				// this line should produce error!
				null.a(1);
				return 11;
			}catch(error){
				return 10;
			}
			return "through";
		}
	
		{
			var result = 9;
			try{
				result = fNormalCatch();
			}catch(e){
				throw e;
				throw "Error should stay in function (11)!";
			}
			if(result !== 10){
				throw new Error("Should be catched!");
			}
		}
	}
	{
		try{
			// this line should produce error!
			null.a();
		}catch(error){
		}
		
		function fEmptyCatch(){
			try{
				// this line should produce error!
				null.a();
			}catch(error){
			}
			return true;
		}
	
		try{
			fEmptyCatch();
		}catch(e){
			throw "Error should stay in function (12)!";
		}
	}
	{
		function fEmptyCatch(){
			try{
				// this line should produce error!
				null.a(1);
			}catch(error){
			}
			return true;
		}
	
		try{
			fEmptyCatch();
		}catch(e){
			throw "Error should stay in function (13)!";
		}
	}

	{
		function fFunctionCatch(){
			// this line should produce error!
			null.a(1);
		}
	
		var result = false;
		try{
			fFunctionCatch();
			throw "No error passed from function!";
		}catch(e){
			result = e != "No error passed from function!";
		}
		if(!result){
			throw "Incorrect catch from function!";
		}
	}
}
out.println("done.");

out.print("varargs calls...");
{
	var x;
	x = Format.sprintf("test1");
	if(x !== "test1"){
		throw "incorrect varargs 1: " + x;
	}
	x = Format.sprintf("test2 %s", "YES");
	if(x !== "test2 YES"){
		throw "incorrect varargs 2: " + x;
	}
	x = Format.sprintf("test3 %s-%s", "NO", "NO");
	if(x !== "test3 NO-NO"){
		throw "incorrect varargs 3: " + x;
	}
	x = Format.sprintf("test4 %s+%s=%s", "2", "3", "5");
	if(x !== "test4 2+3=5"){
		throw "incorrect varargs 4: " + x;
	}
	x = Format.sprintf("test5 %s-%s", ["NO", "NO"]);
	if(x !== "test5 NO-NO"){
		throw "incorrect varargs 5: " + x;
	}
	
	x = 5;
	x = Format.sprintf("test11 0x%04X", x & 0xFFFF);
	if(x !== "test11 0x0005"){
		throw "incorrect varargs 11: " + x;
	}
	
	const Paths = require("java.class/java.nio.file.Paths");

	x = Paths.get("test21", "b", "c").toString();
	if(x !== "test21/b/c"){
		throw "incorrect varargs 21: " + x;
	}

	x = Paths.get("test22", "b").toString();
	if(x !== "test22/b"){
		throw "incorrect varargs 22: " + x;
	}

	x = Paths.get(Paths.get("test30", "b").toUri()).toString();
	if(!x.endsWith("/test30/b")){
		throw "incorrect varargs 30: " + x;
	}
}
out.println("done.");

out.print("Try/finally/break+continue...");
{
	var i = 0;
	for(;i < 2; ++i){
		try{
			break;
		}finally{
			continue;
		}
	}
	if(i !== 2){
		throw "Final continue must overshadow, i=" + i;
	}
}
{
	var i = 0;
	for(;i < 2; ++i){
		try{
			continue;
		}finally{
			break;
		}
	}
	if(i !== 0){
		throw "Final break must overshadow, i=" + i;
	}
}
{
	var i = -1;
	for(;i < 1; ++i){
		try{
			return;
		}finally{
			continue;
		}
	}
	if(i !== 1){
		throw "Final continue must overshadow, i=" + i;
	}
}
{
	var i = -1;
	for(;i < 1; ++i){
		try{
			return;
		}finally{
			break;
		}
	}
	if(i !== -1){
		throw "Final break must overshadow, i=" + i;
	}
}
{
	var i = 1;
	for(;i < 3; ++i){
		try{
			null();
		}finally{
			continue;
		}
	}
	if(i !== 3){
		throw "Final continue must overshadow, i=" + i;
	}
}
{
	var i = 1;
	for(;i < 2; ++i){
		try{
			null();
		}finally{
			break;
		}
	}
	if(i !== 1){
		throw "Final break must overshadow, i=" + i;
	}
}
out.println("done.");

out.print("Try/Catch/break+continue...");
{
	var i = 0;
	for(;i < 2; ++i){
		try{
			null();
		}catch(e){
			continue;
		}
	}
	if(i !== 2){
		throw "Catch continue must overshadow, i=" + i;
	}
}
{
	var i = 0;
	for(;i < 2; ++i){
		try{
			null();
		}catch(e){
			break;
		}
	}
	if(i !== 0){
		throw "Catch break must overshadow, i=" + i;
	}
}
out.println("done.");

out.print("Try/Catch/Finally/break+continue...");
{
	var i = 0;
	for(;i < 2; ++i){
		try{
			null();
		}catch(e){
			break;
		}finally{
			continue;
		}
	}
	if(i !== 2){
		throw "Catch continue must overshadow, i=" + i;
	}
}
{
	var i = 0;
	for(;i < 2; ++i){
		try{
			null();
		}catch(e){
			continue;
		}finally{
			break;
		}
	}
	if(i !== 0){
		throw "Catch break must overshadow, i=" + i;
	}
}
{
	var i = 10;
	for(;i < 12; ++i){
		try{
			null();
		}catch(e){
			return;
		}finally{
			continue;
		}
	}
	if(i !== 12){
		throw "Catch continue must overshadow, i=" + i;
	}
}
{
	var i = 10;
	for(;i < 12; ++i){
		try{
			null();
		}catch(e){
			return;
		}finally{
			break;
		}
	}
	if(i !== 10){
		throw "Catch break must overshadow, i=" + i;
	}
}
{
	var i = 20;
	for(;i < 22; ++i){
		try{
			null();
		}catch(e){
			null();
		}finally{
			continue;
		}
	}
	if(i !== 22){
		throw "Catch continue must overshadow, i=" + i;
	}
}
{
	var i = 20;
	for(;i < 22; ++i){
		try{
			null();
		}catch(e){
			null();
		}finally{
			break;
		}
	}
	if(i !== 20){
		throw "Catch break must overshadow, i=" + i;
	}
}
out.println("done.");

out.print("Catch/finally/shadow(0)...");
{
	var test = 0;
	{
		try{
			try{
				test += 1;
				out.println("       throw...");
				throw new Error("check111");
				test += 1;
			}finally{
				test += 5;
				out.println("       finally...");
			}
		}catch(e){
			test += 2;
			out.println("       catch...");
			if(e.message !== 'check111'){
				out.println("       error 8-( " + Format.jsDescribe(e));
			}else{
				test += 2;
			}
		}
	}
	if( test != 10 ){
		throw new Error("finally error shadow (0) is not working!");
	}
}
out.println("done.");

out.print("Catch/finally/shadow(1)...");
{
	var test = 0;
	{
		try{
			try{
				test += 1;
				out.println("       throw...");
				throw new Error("check111");
				test += 1;
			}finally{
				test += 5;
				out.println("       throw-finally...");
				throw new Error("check222");
			}
		}catch(e){
			test += 2;
			out.println("       catch...");
			if(e.message !== 'check222'){
				out.println("       error 8-( " + Format.jsDescribe(e));
			}else{
				test += 2;
			}
		}
	}
	if( test != 10 ){
		throw new Error("finally error shadow (1) is not working!");
	}
}
out.println("done.");

out.print("Catch/finally/shadow(2)...");
{
	var test = 0;
	{
		try{
			try{
				test += 1;
				throw new Error("check111");
				test += 1;
			}catch(e){
				test += 2;
				throw new Error("check222");
			}finally{
				test += 5;
				throw new Error("check333");
			}
		}catch(e){
			test += 2;
			out.println("       catch...");
			if(e.message !== 'check333'){
				out.println("       error 8-( " + Format.jsDescribe(e));
				throw e;
			}
			test += 2;
		}
	}
	if( test != 12 ){
		throw new Error("finally error shadow (2) is not working!");
	}
}
out.println("done.");



out.print("Catch/finally/shadow(3)...");
{
	var test = '';
	$output(test){
		try{
			try{
				= '11';
				throw new Error("check111");
			}catch(e){
				= '22';
				throw new Error("check222");
			}finally{
				= '33';
				throw new Error("check333");
			}
		}catch(e){
			= '44';
			out.println("       catch...");
			if(e.message !== 'check333'){
				= '55' + e.message;
			}
		}
	}
	if( test != '11223344' ){
		throw new Error("finally error shadow (3) is not working!");
	}
}
out.println("done.");

out.print("Catch/finally/future (1)...");
{
	var test = 0;
	{
		const ae3 = require("ae3");
		const Concurrent = ae3.Concurrent;
		const FutureSimpleObject = Concurrent.FutureSimpleUnknown;
		
		try{
			try{
				test += 1;
				var future = new FutureSimpleObject();
				test += 1;
				future.setError(new Error("check"));
				test += 1;
				future.baseValue();
				test += 100;
			}finally{
				test += 5;
			}
		}catch(e){
			test += 2;
		}
	}
	if( test != 10 ){
		throw new Error("finally is not working for futures, test: " + test);
	}
}
out.println("done.");

out.print("Catch/finally/future (2)...");
{
	var test = [-10];
	{
		out.println("       start...");
		function check(){
			test[0] += 1; // -20
			if(test[0] !== -20){
				throw new Error("'test' reference is not accessible!");
			}
			const ae3 = require("ae3");
			const Concurrent = ae3.Concurrent;
			const FutureSimpleObject = Concurrent.FutureSimpleUnknown;
			try{
				test[0] += 1; // -19
				var future = new FutureSimpleObject();
				test[0] += 1; // -18
				out.println("       setError...");
				future.setError(new Error("check123"));
				test[0] += 1; // -17
				out.println("       baseValue...");
				future.baseValue();
				test[0] += 100;
			}finally{
				out.println("       finally...");
				test[0] += 5; // -12
			}
		}
		
		try{
			test[0] += -11; // -21
			check();
		}catch(e){
			out.println("       catch...");
			if(e.message !== 'check123'){
				out.println("       error 8-( message: " + e.message + ", throwable: " + Format.throwableAsPlainText(e));
				throw e;
			}
			test[0] += 2; // -10
		}
		out.println("       out...");
	}
	out.println("       check...");
	if( test[0] != -10 ){
		throw new Error("finally is not working for futures in function call!");
	}
}
out.println("done.");

out.print("Catch/finally/future (3)...");
{
	var test = -20;
	function check(){
		test += 1;
		if(test !== -30){
			throw new Error("'test' variable is not accessible!");
		}
		const ae3 = require("ae3");
		const Concurrent = ae3.Concurrent;
		const FutureSimpleObject = Concurrent.FutureSimpleUnknown;
		try{
			test += 1;
			var future = new FutureSimpleObject();
			test += 1;
			future.setError(new Error("check12345"));
			test += 1;
			future.baseValue();
			test += 100;
		}finally{
			test += 5;
		}
	}
	
	try{
		test += -11;
		check();
	}catch(e){
		if(e.message !== 'check12345'){
			throw e;
		}
		test += 2;
	}
	if( test != -20 ){
		throw new Error("Variable visibility is fucked for local function scope!");
	}
}
out.println("done.");

out.print("GV access from Function/Catch/finally...");
{
	var test = -30;
	function check(){
		test += 1; // -40
		if(test !== -40){
			throw new Error("'test' variable is not accessible!");
		}
		const ae3 = require("ae3");
		const Concurrent = ae3.Concurrent;
		const FutureSimpleObject = Concurrent.FutureSimpleUnknown;
		try{
			test += 1; // -39
			var future = new FutureSimpleObject();
			test += 1; // -38
			future.setError(new Error("check12345"));
			test += 1; // -37
			future.baseValue();
			test += 100;
		}finally{
			test += 5; // -32
		}
	}
	
	try{
		test += -11; // -41
		check();
	}catch(e){
		if(e.message !== 'check12345'){
			throw e;
		}
		test += 2;
	}
	if( test != -30 ){
		throw new Error("Variable visibility through GV is fucked for local function scope!");
	}
}
/**
 * Currently FSTORE not writing further than GV
 */
if(false){
	var test = -30;
	{
		function check(){
			test += 1; // -40
			if(test !== -40){
				throw new Error("'test' variable is not accessible!");
			}
			const ae3 = require("ae3");
			const Concurrent = ae3.Concurrent;
			const FutureSimpleObject = Concurrent.FutureSimpleUnknown;
			try{
				test += 1; // -39
				var future = new FutureSimpleObject();
				test += 1; // -38
				future.setError(new Error("check12345"));
				test += 1; // -37
				future.baseValue();
				test += 100;
			}finally{
				test += 5; // -32
			}
		}
		
		try{
			test += -11; // -41
			check();
		}catch(e){
			if(e.message !== 'check12345'){
				throw e;
			}
			test += 2;
		}
	}
	if( test != -30 ){
		throw new Error("Variable visibility through GV is fucked for local function scope!");
	}
}
out.println("done.");

out.print("Internal Final Frame check (1)...");
{
	var a = [''];
	try{
		$output(a[0]){
			var b = 1;
			try{
				= '11';
				$output(null){
					var c = 2;
					= 'aaaaa';
				}
				= '12';
			}finally{
				= '23';
			}
		}
	}catch(e){
		if(e != 'check'){
			throw "(1) Invalid exception";
		}
		// e;
	}finally{
		var z = 13;
		var x = z + 1;
	}
	if(a[0] !== '111223'){
		throw new Error('(1) Something wrong, unchecked, a=' + a[0]);
	}
}
out.println("done.");

out.print("Internal Final Frame check (2)...");
{
	var a = ['Hi', 0];
	function check(){
		try{
			$output(a[0]){
				var b = 1;
				a[1] = 1;
				try{
					a[1] = 2;
					= '11';
					$output(null){
						a[1] = 3;
						var c = 2;
						= 'aaaaa';
						return true;
					}
					= 'zz';
				}finally{
					a[1] += 100;
					= '12';
				}
			}
		}catch(e){
			a[1] += 1000;
			if(e != 'check'){
				throw "(2) Invalid exception";
			}
			// e;
		}finally{
			a[1] += 10000;
			var z = 13;
			var x = z + 1;
		}
		a[1] += 100000;
	}
	check();
	if(a[0] !== 'Hi'){
		throw new Error('(2) Something wrong, unchecked, a=' + a[0]);
	}
	if(a[1] !== 10103){
		throw new Error('(2) Something wrong, unchecked, 10103 != ' + a[1]);
	}
}
out.println("done.");

out.print("Internal Final Frame check (3)...");
{
	var a = ['', 0];
	try{
		$output(a[0]){
			var b = 1;
			a[1] = 11;
			try{
				a[1] = 12;
				= '11';
				a[1] = 13;
				$output(null){
					a[1] = 14;
					var c = 2;
					= 'aaaaa';
					a[1] = 15;
					throw 'check';
					a[1] = 16;
				}
				a[1] = 17;
				= 'zz';
				a[1] = 18;
			}finally{
				a[1] += 100;
				= '12';
			}
		}
	}catch(e){
		a[1] += 1000;
		if(e != 'check'){
			throw "(3) Invalid exception";
		}
		// e;
	}finally{
		a[1] += 10000;
		var z = 13;
		var x = z + 1;
	}
	a[1] += 100000;
	if(a[0] !== ''){
		throw new Error('(3) Something wrong, unchecked, a=' + a[0]);
	}
	if(a[1] !== 111115){
		throw new Error('(3) Something wrong, unchecked, 111115 != ' + a[1]);
	}
}
out.println("done.");


out.print("Precedence text...");
{
	var a = 2, b = 3, c = 4;
	if((a + b * c) != (a + (b * c))){
		throw (a + b * c) + " not equals " + (a + (b * c));
	}
}
{
	function test(a,b,c){
		if((a + b * c) != (a + (b * c))){
			throw (a + b * c) + " not equals " + (a + (b * c));
		}
	}
	test(2,3,4);
	function test(a,b,c){
		if((a || b && c) != (a || (b && c))){
			throw (a || b && c) + " not equals " + (a || (b && c));
		}
	}
	test(2,3,4);
	test(0,3,4);
	test(2,0,4);
	test(2,3,0);
}
out.println("done.");

out.print("DATA TO DATA compare...");
{
	var a = 5;
	var b = 5;
	if(!a){
		throw;
	}
	if(a < b){
		throw;
	}
	if(a > b){
		throw;
	}
	if(a != b){
		throw;
	}
	if(!(a == b)){
		throw;
	}
	if(!(a === b)){
		throw;
	}
	if(a - b != 0){
		throw;
	}
	if(5 + 5 * 5 != 30){
		throw;
	}
	var c = 5;
	if(a + b * c != 30){
		throw;
	}
}
out.println("done.");

out.print("INCREMENT / DECREMENT CHECK...");
{
	var test1 = 0;
	test1 ++;
	test1 ++;
	if( test1 != 2 ){
		throw "increment post doesn't work!";
	}
	test1 --;
	test1 --;
	if( test1 ){
		throw "decrement post doesn't work!";
	}
}
{
	var test1 = false;
	test1 ++;
	test1 ++;
	if( test1 != 2 ){
		throw "increment post doesn't work!";
	}
	test1 --;
	test1 --;
	if( test1 ){
		throw "decrement post doesn't work!";
	}
	{
		var temp = 2, five = 5;
		test1 = 0;
		temp = temp + test1 ++;
		temp = temp * test1 ++;
		temp = test1 ++ - temp;
		temp = test1 ++ + temp;
		if( test1 != 4 ){
			throw "increment post doesn't work, expected 3, but: " + test1;
		}
		// 2 + 0 = 2 ;; 2 * 1 = 2 ;; 2 - 2 = 0 ;; 3 + 0 = 3
		if( temp != 3 ){
			throw "increment post doesn't work, expected 3, but: " + temp;
		}
		temp = temp + test1 --;
		temp = temp * test1 --;
		temp = test1 -- + temp;
		temp = test1 -- - temp;
		if( test1 !== 0 ){
			throw "decrement post doesn't work, expected 1, but: " + test1;
		}
		// 3 + 4 = 7 ;; 7 * 3 = 21 ;; 2 + 21 = 23 ;; 1 - 23 = -22
		if( temp != -22 ){
			throw "decrement post doesn't work, expected -22, but: " + temp;
		}
	}
}
{
	{
		var test1 = false;
		++ test1;
		++ test1;
		if( test1 != 2 ){
			throw "increment pre doesn't work, test1=" + test1;
		}
		-- test1;
		-- test1;
		if( test1 ){
			throw "decrement pre doesn't work, test1=" + test1;
		}
	}
	{
		var test1 = 0;
		++ test1;
		++ test1;
		if( test1 != 2 ){
			throw "increment pre doesn't work, test1=" + test1;
		}
		-- test1;
		-- test1;
		if( test1 ){
			throw "decrement pre doesn't work, test1=" + test1;
		}
	}
	{
		/**
		 * no toInt 8-)
		 */
		var n = 1.5;
		if(--n != 0.5){
			throw "expected to be 0.5, but: " + n;
		}
	}
	{
		/**
		 * within expression
		 */
		var n = 1;
		if(--n != 0){
			throw "expected to be zero";
		}
		if(++n != 1){
			throw "expected to be one";
		}
		if(--n > 0){
			throw "expected to be zero";
		}
		if(++n < 1){
			throw "expected to be one";
		}
	}
}
{
	var counter = 15;
	var a = counter ++;
	if(a != 15){
		throw "Must be 15, but: " + a;
	}
	if(counter != 16){
		throw "Must be 16, but: " + counter;
	}
	a = counter --;
	if(a != 16){
		throw "Must be 16, but: " + a;
	}
	if(counter != 15){
		throw "Must be 15, but: " + counter;
	}
	a = -- counter;
	if(a != 14){
		throw "Must be 14, but: " + a;
	}
	if(counter != 14){
		throw "Must be 14, but: " + counter;
	}
	a = ++ counter;
	if(a != 15){
		throw "Must be 15, but: " + a;
	}
	if(counter != 15){
		throw "Must be 15, but: " + counter;
	}
}
{
	var object = { counter : 15 };
	var a = object.counter ++;
	if(a != 15){
		throw "Must be 15, but: " + a;
	}
	if(object.counter != 16){
		throw "Must be 16, but: " + object.counter;
	}
	a = object.counter --;
	if(a != 16){
		throw "Must be 16, but: " + a;
	}
	if(object.counter != 15){
		throw "Must be 15, but: " + object.counter;
	}
	a = -- object.counter;
	if(a != 14){
		throw "Must be 14, but: " + a;
	}
	if(object.counter != 14){
		throw "Must be 14, but: " + object.counter;
	}
	a = ++ object.counter;
	if(a != 15){
		throw "Must be 15, but: " + a;
	}
	if(object.counter != 15){
		throw "Must be 15, but: " + object.counter;
	}
	a = 20 - ++ object.counter;
	if(a != 4){
		throw "Must be 4, but: " + a;
	}
	if(object.counter != 16){
		throw "Must be 16, but: " + object.counter;
	}
	a = 5 + -- object.counter;
	if(a != 20){
		throw "Must be 20, but: " + a;
	}
	if(object.counter != 15){
		throw "Must be 15, but: " + object.counter;
	}
	a = 4 + ++ object.counter;
	if(a != 20){
		throw "Must be 20, but: " + a;
	}
	if(object.counter != 16){
		throw "Must be 16, but: " + object.counter;
	}
	a = 4 + object.counter --;
	if(a != 20){
		throw "Must be 20, but: " + a;
	}
	if(object.counter != 15){
		throw "Must be 16, but: " + object.counter;
	}
	a = 5 + object.counter ++;
	if(a != 20){
		throw "Must be 20, but: " + a;
	}
	if(object.counter != 16){
		throw "Must be 16, but: " + object.counter;
	}
}
out.println("done.");

out.print("'typeof' keyword test...");
{
	if(typeof aaa != "undefined"){
		throw "typeof undefined object should return string 'undefined'";
	}
	if(typeof undefined != "undefined"){
		throw "typeof undefined object should return string 'undefined'";
	}
	if(typeof null != "object"){
		throw "typeof null object should return string 'object', but: " + (typeof null);
	}
	/**
	 * number
	 */
	var number = 5;
	if(typeof number != "number"){
		throw "typeof variable containing a number should return string 'number', but: " + (typeof number);
	}
	if(typeof 5 != "number"){
		throw "typeof 5 should return string 'number', but: " + (typeof 5);
	}
	{
		/**
		 * just to play with parser
		 */
		if(typeof (number) != "number"){
			throw "typeof variable containing a number should return string 'number', but: " + (typeof number);
		}
		/**
		 * just to play with parser
		 */
		if(typeof(number) != "number"){
			throw "typeof variable containing a number should return string 'number', but: " + (typeof number);
		}
	}
	/**
	 * boolean
	 */
	var boolean = true;
	if(typeof boolean != "boolean"){
		throw "typeof variable containing a boolean value should return string 'boolean', but: " + (typeof boolean);
	}
	if(typeof true != "boolean"){
		throw "typeof true should return string 'boolean', but: " + (typeof true);
	}
	if(typeof false != "boolean"){
		throw "typeof false should return string 'boolean', but: " + (typeof false);
	}
	if(typeof (number > 1) != "boolean"){
		throw "typeof boolean expression result should return string 'boolean', but: " + (typeof (number > 1));
	}
	/**
	 * string
	 */
	var string = "string";
	if(typeof string != "string"){
		throw "typeof variable containing a string should return string 'string'";
	}
	if(typeof 'string' != "string"){
		throw "typeof 'string' should return string 'string'";
	}
	{
		var result = typeof number + "string".substring(1);
		if(result != "numbertring"){
			throw "typeof number with addition string to number should return string 'numbertring', result=" + result;
		}
	}
	{
		var result = typeof (number + "string".substring(1));
		if(result != "string"){
			throw "typeof expression of addition string to number should return string 'string', result=" + result;
		}
	}
	/**
	 * function
	 */
	{
		function fn(){
		}
		var result = typeof fn;
		if(result != "function"){
			throw "typeof variable containing a function should return string 'function', result=" + result;
		}
	}
}
out.println("done.");

out.print("'void' keyword test...");
{
	{
		if(void true !== undefined){
			throw "void: 'void true' #1 is not undefined, " + (void true);
		}
		if(undefined !== void true){
			throw "void: 'void true' #2 is not undefined, " + (void true);
		}
	}
	{
		var x = true;
		if(void x !== undefined){
			throw "void: 'void x' #1 is not undefined, " + (void x);
		}
		if(undefined !== void x){
			throw "void: 'void x' #2 is not undefined, " + (void x);
		}
	}
	{
		if( (void 2 == '2') !== false){
			throw "void: 'void 2 =='2'', operator precedence, " + (void 2 == '2');
		}
		if( (void (2 == '2')) !== undefined){
			throw "void: 'void (2 =='2')', operator precedence, " + (void (2 == '2'));
		}
	}
	{
		var x = 5;
		void function life(){
			++x;
		}();
		if(6 !== x){
			throw "void: 'function call' didn't run, x=" + x;
		}
	}
}
out.println("done.");

out.print("'delete' keyword test...");
{
	{
		bzzz = "test";
		delete bzzz;
		if(bzzz){
			throw "global variable 'bzzz' should be deleted, bzzz=" + bzzz;
		}
	}
	{
		var a = {
			field0 : "000"
		};
		a.field1 = "test";
		if(!a.field1){
			throw "field should be defined!!!";
		}
		delete a.field1;
		if(a.field1){
			throw "field should be deleted!!!";
		}
		if(!a.field0){
			throw "field should be defined!!!";
		}
		delete a.field0;
		if(a.field0){
			throw "field should be deleted!!!";
		}
		/** not yet %-( it is 'var's problem
		 * <code>
		delete a;
		if(!a){
			throw "locally declared variable should not be deleted!!!";
		}
		 </code>
		 */
	}
	{
		var f = function(){
			if(this.aaa){
				throw "Sould not be defined yet!";
			}
			this.aaa = 55;
			if(this.aaa != 55){
				throw "Should be 55 now, but: " + this.aaa;
			}
			delete this.aaa;
			if(this.aaa){
				throw "Should not be defined already!";
			}
		};
		f.call({});
	}
	{
		var arr = [1,2];
		if(arr[1] !== 2){
			throw "Should be equal to 2";
		}
		delete arr[1];
		if(arr[1] === 2){
			throw "Should not be equal to 2";
		}
	}
	/**
	 * something complicated
	 */
	{
		var o = { zzz : 15 }, k1 = 'zz';
		if(o[k1 + 'z'] != 15){
			throw "should be 15, but: " + o[k1 + 'z'] + ", key=" + (k1 + 'z');
		}
		var r1 = delete o[k1 + 'z'];
		if(!r1){
			throw "delete should return true!";
		}
		if(o[k1 + 'z']){
			throw "should be deleted";
		}
		var r2 = delete o[k1 + 'z'];
		if(!r2){
			throw "delete should return true!";
		}
		var r3 = delete o[k1 + 'ZZ'];
		if(!r3){
			throw "delete should return true!";
		}
	}
	/**
	 * something even more complicated
	 */
	{
		var o = { oo : { zzz : 15 } }, k1 = 'zz';
		if(o.oo.zzz != 15){
			throw "should be 15!";
		}
		if(o.oo['zzz'] != 15){
			throw "should be 15!";
		}
		if(o.oo[k1 + 'z'] != 15){
			throw "should be 15!";
		}
		var r1 = delete o.oo[k1 + 'z'];
		if(!r1){
			throw "delete should return true!";
		}
		if(o.oo[k1 + 'z']){
			throw "should be deleted";
		}
		var r2 = delete o.oo[k1 + 'z'];
		if(!r2){
			throw "delete should return true!";
		}
		var r3 = delete o.oo[k1 + 'ZZ'];
		if(!r3){
			throw "delete should return true!";
		}
	}
	/**
	 * something even more complicated
	 */
	{
		var o = { oo : { zzz : 15 } }, k1 = 'zz', k2 = 'o';
		if(o[k2 + 'o'].zzz != 15){
			throw "should be 15!";
		}
		if(o[k2 + 'o']['zzz'] != 15){
			throw "should be 15!";
		}
		if(o[k2 + 'o'][k1 + 'z'] != 15){
			throw "should be 15!";
		}
		var r1 = delete o[k2 + 'o'][k1 + 'z'];
		if(!r1){
			throw "delete should return true!";
		}
		if(o[k2 + 'o'][k1 + 'z']){
			throw "should be deleted";
		}
		var r2 = delete o[k2 + 'o'][k1 + 'z'];
		if(!r2){
			throw "delete should return true!";
		}
		var r3 = delete o[k2 + 'o'][k1 + 'ZZ'];
		if(!r3){
			throw "delete should return true!";
		}
	}
}
out.println("done.");

out.print("recursive function...");
// out.print("arguments.cal-lee...");
{
	/**
	 * arguments object
	 */
	function factorial(x){
		return x <= 1 ? 1 : x * factorial(x - 1);
		// return x <= 1 ? 1 : x * arguments.cal-lee(x - 1);
	}
	
	var x = factorial(3);
	if(x != (3*2*1)) throw "callee?, x=" + x;
}
out.println("done.");

out.print("Nested Maps...");
{
	var map = {
		trash : {},
		"new" : {}
	};
	if(!map.trash){
		throw "Field should be defined!";
	}
	if(map.hrash){
		throw "Field should be undefined!";
	}
	if(!map.new){
		throw "Field should be defined!";
	}
	if(!map["new"]){
		throw "Field should be defined!";
	}
}
out.println("done.");

out.print("OUTPUT...");
{
	var test;
	var test4;
	var src11111 = '11111';
	var src33333 = function(){
		return "33333";
	};
	$output( test ){
		= src11111;
		$output( null ){
			= "22222";
		}
		= src33333();
		$output( test4 ){
			= "44444";
		}
		= "55555";
	}

	if( test != "111113333355555"){
		throw "OUTPUT fail: " + test;
	}

	if( test4 != "44444"){
		throw "OUTPUT fail: " + test4;
	}
}
/**
 * multiple output
 */
{
	var test;
	$output(test){
		= (= 'aaa');
	}
	if(test != 'aaaaaa'){
		throw "OUTPUT fail: " + test;
	}
	var str = 'abc';
	$output(test){
		= (= (=str)+',');
	}
	if(test != 'abcabc,abc,'){
		throw "OUTPUT fail: " + test;
	}
}
out.println("done.");

out.print("CALLV...");
{
	{
		// BOOLEAN TRUE
		function f(){
			return true;
		}
		
		if(!f){
			throw "No function after declaration";
		}
		var result = f();
		if(result != true){
			throw "Wrong function (1) result: " + result;
		}
		if(f() != true){
			throw "Wrong function (2) result: " + f();
		}
	}
	{
		// BOOLEAN FALSE
		function f(){
			return false;
		}
		
		if(!f){
			throw "No function after declaration";
		}
		var result = f();
		if(result != false){
			throw "Wrong function (3) result: " + result;
		}
		if(f() != false){
			throw "Wrong function (4) result: " + f();
		}
	}
	{
		// STRING
		function f(){
			return "OK";
		}
		
		if(!f){
			throw "No function after declaration";
		}
		var result = f();
		if(result != "OK"){
			throw "Wrong function (5) result: " + result;
		}
		if(f() != "OK"){
			throw "Wrong function (6) result: " + f();
		}
	}
}
out.println("done.");

out.print("CALLO...");
{
	var o = "CHECK";
	
	if(o != "CHECK"){
		throw "Value is not equals to value used to create this value";
	}
	
	function f(o){
		if(o != "CHECK"){
			throw "Argument is not equal to value (1), value=" + o;
		}
		return true;
	}
	
	if(!f(o)){
		throw "argument passing is broken!";
	}

	/**
	 * Extra ACM.CMS named parameters (instead of an object)
	 * 
	 * Shite! Name should match to a declared name (they are not mapped as indexed arguments)
	 */
	if(!f( o = o )){
		throw "argument passing is broken!";
	}

	function f(ox){
		if(ox != "CHECK"){
			throw "Argument is not equals to value (2)";
		}
		var o = "check";
		return true;
	}
	
	if(!f(o)){
		throw "argument passing is broken!";
	}
	
	if(o != "CHECK"){
		throw "Value is not shadowed during function call";
	}
	
	/**
	 * Extra ACM.CMS named parameters (instead of an object)
	 * 
	 * Shite! Name should match to a declared name (they are not mapped as indexed arguments)
	 */
	if(!f( ox = o )){
		throw "argument passing is broken!";
	}
	
}
out.println("done.");

out.print("'this' value for function...");
{
	var a, b, c;
	(function(){
		this.fuck = true;
	})();
	if(fuck !== true){
		throw "Spec: this should be global object!";
	}
	delete fuck;
}
{
	var a, b, c;
	function f(){
		this.fuck = true;
	}
	f();
	if(fuck !== true){
		throw "Spec: this should be global object!";
	}
	delete fuck;
}
{
	if(TEST_GLOBAL){
		throw "Expected to be undefined!";
	}
	TEST_GLOBAL = 4218410;
	function f1(){
		return this;
	}
	function f2(aaa){
		return this;
	}
	function f3(aaa, bbb){
		return this;
	}
	for each(var f in [
	                   f1, 
	                   f2, 
	                   f3, 
	                   function(){return this;}, 
	                   function(a){return this;}, 
	                   function(a,b){return this;}
	                   ]){
		if( f().TEST_GLOBAL != TEST_GLOBAL){
			throw "Spec: should be 'global', but: " + f();
		}
		if( f.call(undefined).TEST_GLOBAL != TEST_GLOBAL){
			throw "Spec: should be 'global', but: " + f.call(undefined);
		}
		if( f.apply(undefined).TEST_GLOBAL != TEST_GLOBAL){
			throw "Spec: should be 'global', but: " + f.apply(undefined);
		}
		if( f.call(null).TEST_GLOBAL != TEST_GLOBAL){
			throw "Spec: should be 'global', but: " + f.call(null);
		}
		if( f.apply(null).TEST_GLOBAL != TEST_GLOBAL){
			throw "Spec: should be 'global', but: " + f.apply(null);
		}
		if( f.call('') !== '' ){
			throw "Should be '', but: " + f.call('');
		}
		if( f.apply('') !== '' ){
			throw "Should be '', but: " + f.apply('');
		}
		{
			var obj = {};
			obj.f = f;
			if( obj.f() != obj ){
				throw "This is expected to be equal to object, but: " + obj.f();
			}
			if( obj.f(0) != obj ){
				throw "This is expected to be equal to object, but: " + obj.f(0);
			}
		}
		{
			var obj = { f : f };
			obj.f = f;
			if( obj.f() != obj ){
				throw "This is expected to be equal to object, but: " + obj.f();
			}
			if( obj.f(0) != obj ){
				throw "This is expected to be equal to object, but: " + obj.f(0);
			}
		}
	}
	delete TEST_GLOBAL;
}
out.println("done.");

out.print("call argument order...");
{
	var a = 1;
	var b = 2;
	var c = 3;
	
	if(!(a == 1 && b == 2 && c == 3)){
		throw;
	}
	
	function f( aa, bb, cc ){
		if(!(aa == 1 && bb == 2 && cc == 3)){
			throw "arguments doesn't match in order or value";
		}
	}
	
	out.print("CALLL3, list...");
	f(a, b, c);
	out.println("done.");
	out.print("CALLL3, list extra...");
	f(a, b, c, a, b, c);
	out.println("done.");
	out.print("CALLL3, map...");
	f(aa = a, bb = b, cc = c);
	out.println("done.");
	out.print("CALLL3, shuffled map...");
	f(bb = b, aa = a, cc = c);
	out.println("done.");
}
out.println("done.");

out.print("call ACM.CMS named arguments...");
{
	function f(diablo, countach, cheetah){
		if(diablo != "diablo"){
			throw "diablo is " + diablo;
		}
		if(countach != "countach"){
			throw "countach is " + countach;
		}
		if(cheetah != "cheetah"){
			throw "cheetah is " + cheetah;
		}
		return "OK";
	}
	
	if( diablo == "diablo" ){
		throw "Not now - should not be equal or even defined!";
	}
	
	if( f( "diablo", "countach", "cheetah" ) != "OK" ){
		throw "Oops!";
	}
	
	if( f( diablo = "diablo", countach = "countach", cheetah = "cheetah" ) != "OK" ){
		throw "Oops!";
	}
	
	if( f( allegro = "allegro", diablo = "diablo", countach = "countach", cheetah = "cheetah" ) != "OK" ){
		throw "Oops!";
	}
	
	if( f( countach = "countach", diablo = "diablo", cheetah = "cheetah" ) != "OK" ){
		throw "Oops!";
	}
	
	if( diablo == "diablo" ){
		throw "Should not be equal or even defined!";
	}
	
	if( allegro == "allegro" ){
		throw "Should not be equal or even defined!";
	}
}
out.println("done.");

out.print("Anonymous recursive function...");
{
	import java.lang.Thread;
	const ROOT_GRP = (function ff(g){ return g.parent ? ff(g.parent) : g; })(Thread.currentThread().threadGroup);

	if(!ROOT_GRP){
		throw "Something's wrong!";
	}
}
out.println("done.");

out.print("INLINE ARRAY...");
{
	// normal style
	{
		var arr = [-1, 1];
		if( !arr ){
			throw "Inline array is undefined =[-1,1]";
		}
		if( !arr.length ){
			throw "Inline array [-1,1] has no length property";
		}
		if( arr.length != 2 ){
			throw "Inline array [-1,1] has length property != 2: (" + arr.length + ")";
		}
		if( arr[0] != -1 ){
			throw "Inline array - first element is not -1 (" + arr[0] + ") [-1,1]";
		}
		if( arr[1] != 1 ){
			throw "Inline array - second element is not 1 (" + arr[1] + ") [-1,1]";
		}
		if( typeof arr != 'object' ){
			throw "inline array doesn't work, typeof (1) == " + (typeof arr) + "!";
		}
	}
	// empty array
	{
		var arr1 = [];
		if( typeof arr1 != 'object' ){
			throw "inline array doesn't work, typeof (2) == " + (typeof arr1) + "!";
		}
	}
	// obsolete style
	if(false){
		var arr = (-1, 1);
		if( !arr ){
			throw "Inline array is undefined =(-1,1)";
		}
		if( !arr.length ){
			throw "Inline array (-1,1) has no length property";
		}
		if( arr.length != 2 ){
			throw "Inline array (-1,1) has length property != 2: (" + arr.length + ")";
		}
		if( arr[0] != -1 ){
			throw "Inline array - first element is not -1 (" + arr[0] + ") (-1,1)";
		}
		if( arr[1] != 1 ){
			throw "Inline array - second element is not 1 (" + arr[0] + ") (-1,1)";
		}
		if( typeof arr != 'object' ){
			throw "inline array doesn't work, typeof (3) == " + (typeof arr) + "!";
		}
	}
	// indexing
	{
		var arr1 = ['aa','bb','cc','dd'];
		if( arr1[1] != 'bb' ){
			throw "inline array indexing doesn't work, should be 'bb' but "+arr1[1]+"!";
		}
		var index = 1;
		if( arr1[index] != 'bb' ){
			throw "inline array indexing doesn't work, should be 'bb' but "+arr1[1]+"!";
		}
		{
			import java.lang.System;
			if(!System){
				throw "import doesn't work!";
			}
			var foundFlag = false;
			foundFlag = foundFlag || (System.currentTimeMillis() == arr1[index])
			if(foundFlag){
				throw "Should not be here, foundFlag should be false!";
			}
		}
	}
	// map 8-) and Math
	{
		/**
		 * calculates greatest 'power of two' less or equal to arbitrary positive integer (like number of CPUs).
		 */
		var arr1 = [8,6,4,3,2,1].map(function(x){ return Math.pow(2, Math.floor(Math.log(x)/Math.log(2))); });
		if(arr1.length != 6){
			throw "Length fucked after call to array's 'map' method: arr=" + arr1;
		}
		var joined1 = arr1.join('');
		if(joined1.length != 6){
			throw "Length fucked after call to array's 'join' method: joined=" + joined1 + ", arr=" + arr1;
		}
		if(joined1 != '844221'){
			throw "'Math' functions must be failing: joined=" + joined1;
		}
	}
	{
		/**
		 * calculates greatest 'power of two' less or equal to arbitrary positive integer (like number of CPUs).
		 */
		const arr2 = [8,6,4,3,2,1].map(function(x){ return 2 ** Math.floor(Math.log(x)/Math.log(2)); });
		if(arr2.length != 6){
			throw "Length fucked after call to array's 'map' method: arr=" + arr2;
		}
		const joined2 = arr2.join('');
		if(joined2.length != 6){
			throw "Length fucked after call to array's 'join' method: joined=" + joined2 + ", arr=" + arr2;
		}
		if(joined2 != '844221'){
			throw "'Math' functions must be failing: joined=" + joined2;
		}
	}
	{
		var a = '' + ['a', 'b', 'c', 'd'].map(function (x){
			return x + 'z' + x;
		});
		if(a !== 'aza,bzb,czc,dzd'){
			throw "Map is not what expected: " + a;
		}
	}
	{
		const o = {
			'0' : 'a', 
			'1' : 'b', 
			'2' : 'c', 
			'3' : 'd',
			'length' : 4
		};
		const f = function (x){
			return x + 'X' + x;
		};
		var a = '' + Array.prototype.map.call(o, f);
		if(a !== 'aXa,bXb,cXc,dXd'){
			throw "Map is not what expected: " + a;
		}
	}
	// flatMap - basic case, flattens one level
	{
		var arr1 = [1,2,3].flatMap(function(x){ return [x, x * 10]; });
		if(arr1.length != 6){
			throw "Length fucked after call to array's 'flatMap' method: arr=" + arr1;
		}
		var joined1 = arr1.join(',');
		if(joined1 != '1,10,2,20,3,30'){
			throw "'flatMap' is not what expected: joined=" + joined1;
		}
	}
	// flatMap - empty array
	{
		var arr1 = [].flatMap(function(x){ return [x, x]; });
		if(arr1.length != 0){
			throw "'flatMap' on empty array should give empty array: arr=" + arr1;
		}
	}
	// flatMap - callback returning a non-array value must not be flattened
	{
		var arr1 = [1,2,3].flatMap(function(x){ return x * 10; });
		if(arr1.length != 3){
			throw "Length fucked after call to array's 'flatMap' method (non-array result): arr=" + arr1;
		}
		var joined1 = arr1.join(',');
		if(joined1 != '10,20,30'){
			throw "'flatMap' is not what expected (non-array result): joined=" + joined1;
		}
	}
	{
		var a = '' + ['a', 'b', 'c', 'd'].flatMap(function (x){
			return [x, x + x];
		});
		if(a !== 'a,aa,b,bb,c,cc,d,dd'){
			throw "flatMap is not what expected: " + a;
		}
	}
	{
		const o = {
			'0' : 'a',
			'1' : 'b',
			'2' : 'c',
			'3' : 'd',
			'length' : 4
		};
		const f = function (x){
			return [x, x.toUpperCase()];
		};
		var a = '' + Array.prototype.flatMap.call(o, f);
		if(a !== 'a,A,b,B,c,C,d,D'){
			throw "flatMap is not what expected: " + a;
		}
	}
}
out.println("done.");

out.print("ARRAY loop test...");
{
	var count = 0;
	var values = "";
	for( var i in [1,2,3,4,5,6] ){
		count && (values += ", ");
		values += i;
		count ++;
	}
	if( count != 6 ){
		throw "6 loops expected but count=" + count + ", values=" + values;
	}
}
// obsolete code
if(___ECMA_IMPL_VERSION_NUMBER___ == 677){
	var count = 0;
	var values = "";
	for( var i in (1,2,3,4,5) ){
		count && (values += ", ");
		values += i;
		count ++;
	}
	if( count != 5 ){
		throw "5 loops expected but count=" + count + ", values=" + values;
	}
}
{
	var testCheck = 0 + 1 + 2 + 3 + 4 + 5;
	var testResult = 0;
	var testArray = [1,2,3,4,5];
	for( var current in testArray ){
		testResult += current;
	}
	if( testResult != testCheck ){
		throw;
	}
	if(testArray.length != testArray.size()){
		throw "By the way: array.length != array.size(): length is " 
			+ testArray.length 
			+ ", while size is " 
			+ testArray.size();
	}
	var counter = testArray.length * testArray.size();
	for( var c1 in testArray ){
		for( var c2 in testArray ){
			counter -= 1;
		}
	}
	if(counter){
		throw;
	}
}
{
	var testMatrix = [ [1,0], [0,1], [0,0], [1,1], [-1,0], [0,-1], [-1,-1] ];
	var counter = 0, value = 0;
	for each( var i in testMatrix ){
		for each( var j in i ){
			counter ++;
			value += j;
		}
	}
	if(counter != 14){
		throw "counter is not 14, but: " + counter;
	}
	if(value != 0){
		throw "value is not 0, but: " + value;
	}
}
out.println("done.");

out.print("SWITCH simple test...");
{
	{
		var a1 = 77;
		switch(a1){
		case 1:
			a1 = 55;
		case 2:
			a1 = 66;
		case 3:
			a1 = 44;
		}
		if(a1 != 77){
			null.error();
			throw "switch simple 1 failed: " + a1;
		}
	}
	{
		var a1 = 77;
		switch(a1){
		case 1:
			a1 = 55;
		case 2:
			a1 = 66;
		case 3:
			a1 = 44;
		case 77:
			break;
		}
		if(a1 != 77){
			null.error();
			throw "switch simple 1 failed: " + a1;
		}
	}
	{
		var a1 = 77;
		switch(a1){
		case 1:
			a1 = 55;
		case 2:
			a1 = 66;
		case 3:
			a1 = 44;
		case 77:
			break;
		default:
			a1 = 88;
		}
		if(a1 != 77){
			null.error();
			throw "switch simple 1 failed: " + a1;
		}
	}
	{
		var a2 = 77;
		switch(a2){
		case 1:
			a2 = 55;
		default:
			a2 = 33;
		}
		if(a2 != 33){
			throw "switch simple 2 failed: " + a2;
		}
	}
	{
		var a3 = 77;
		switch(a3){
		case 1:
		case 2:
		case 77:
		case 78:
			a3 = 55;
		default:
			a3 = 31;
		}
		if(a3 != 31){
			throw "switch simple 3 failed: " + a3;
		}
	}
	{
		var a41 = 77;
		switch(a41){
		case 1:
		case 2:
		case 77:
		case 78:
			a41 = 55;
			break;
		default:
			a41 = 31;
		}
		if(a41 != 55){
			null.error();
			throw "switch simple a41 failed: " + a41;
		}
	}
	{
		var a42 = 77;
		switch(a42){
		case 1:
		case 2:
		case 77:
		case 78: {
			// block bla-bla-bla
			a42 = 55;
			break;
		}
		default:{
			a42 = 31;
		}
		if(a42 != 55){
			null.error();
			throw "switch simple a42 failed: " + a42;
		}
	}
}
out.println("done.");

out.print("SWITCH recursion test...");
{
	var a = 15;
	switch(a){
		case 1:
			switch(ass){
				case 1:
					ff = 1;
				case 15:
					fff = 2;
				case 16:
					zzz = 3;
					break;
			}
			d=1;
			break;
		case 2:
			d=2;
			break;
		case 3:
			d=3;
			break;
		case 15:
			switch(a){
				case 1:
					ff = 1;
				case 15:
					fff = 2;
				case 16:
					zzz = 3;
					break;
				default:
					ccc = 4;
			}
			c = false ? 1 : 2;
			break;
		case 16:
			e = 5;
			f = 6;
			break;
		default:
			d="default";
	}

	if( a != 15 || c != 2 || fff != 2 || zzz != 3){
		throw;
	}
	out.println("done.");

	out.print("SWITCH passthrough test...");
	var c = 1;
	if( c != 1 ){
		throw "unable to reset constant value!";
	}
	// with default
	{
		var test = false;
		switch(c){
		case 0:
			throw;
		case 1:
			test ++;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		default:
			throw;
		}
		if(test != 2){
			throw;
		}
	}
	{
		var test = false;
		switch(c){
		case 0:
			throw;
		case 1:
			test ++;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		}
		if(test != 2){
			throw;
		}
	}
	// with default
	{
		var test = false;
		switch(c){
		case 0:
			throw;
		case 1:
			test ++;
			break;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		default:
			throw;
		}
		if(test != 1){
			throw;
		}
	}
	{
		var test = false;
		switch(c){
		case 0:
			throw;
		case 1:
			test ++;
			break;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		}
		if(test != 1){
			throw;
		}
	}
	// with default
	{
		var test = false;
		switch (c){
		case 1:
			test ++;
			break;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		default:
			throw;
		}
		if(test != 1){
			throw;
		}
	}
	{
		var test = false;
		switch (c) {
		case 1:
			test ++;
			break;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		}
		if(test != 1){
			throw;
		}
	}
	// with default
	{
		var test = false;
		switch(c){
		case 0:
			throw;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		default:
			test = 5;
		}
		if(test != 5){
			throw;
		}
	}
	{
		var test = false;
		switch(c){
		case 0:
			throw;
		case 2:
			test ++;
			break;
		case 3:
			throw;
		}
		if(test != false){
			throw;
		}
	}
	{
		var test = 0;
		for(var i = 3; i > 0; i--){
			switch(i){
			case 3:
			case 2:
			case 1:
				test++;
				break;
			case 0:
				throw "i shouldn't be zero!";
			}
		}
		if(test != 3){
			throw "switch passthough error, test value should be 3, but it is " + test;
		}
	}
}
out.println("done.");

out.print("SWITCH optimizer tests...");
{
	function f1(){
		switch(a){
			default:
				return 1;
		}
	}
	function f2(){
		var b = 2;
		switch(2){
			case 2:
				return 1;
		}
	}
	function f3(){
		var b = 3;
		switch(b){
			case 3:
				return 1;
		}
	}
	function f4(){
		var b = 3;
		switch(b){
			case 3:{
				var c = b;
				return c / 3;
			}
		}
	}
	function f5(){
		var b = 3;
		switch(b){
			case 3:{
				var c = b;
				try{
					throw;
				}catch(e){
					return c / 3;
				}
			}
		}
	}
	if( f1() + f2() + f3() + f4() + f5() != 5 ){
		throw;
	} 
	out.println("done.");
	out.print("SWITCH default tests...");
	{
		switch(a){
			case 1:
				break;
			case 2:
				break;
			default:{
				var b = a + 2;
			}
			break;
		}
		switch(a){
			case 1:
				break;
			case 2:
				break;
			default:
				break;
		}
	}
}
out.println("done.");

out.print("eval check...");
{
	var object = {};
	function ff(param0, userEntry){
		return userEntry;
	}
	object.ff = ff;
	var command = "ff";
	var result1 = eval("object." + command + "(userEntry = 'CHECK')");
	if( result1 != "CHECK" ){
		throw "result is not 'CHECK' but: " + result1;
	}
}
for each(var s in [
                   '{a:5,b:6,c:{aa:5,bb:6},d:{aaa:5,bbb:6},e:7}',
                   '{"a":5,"b":6,c:{"aa":5,"bb":6},"d":{"aaa":5,"bbb":6},"e":7}',
                   '{a:5,b:6,c:{aa:5,bb:6},d:{aaa:5,bbb:6}}',
                   '{"a":5,"b":6,c:{"aa":5,"bb":6},"d":{"aaa":5,"bbb":6}}',
                   '{a:5,b:6,c:{aa:5},cc:{bb:6},d:{aaa:5},dd:{bbb:6},e:{ee:99}}',
                   '{"a":5,"b":6,"s":1.377579693396E9,"u":0,"ndm-ndls":{"r":1,"d":1},"ndm-ndss":{"r":0,"d":0},"d-d":{"s":Date(\'2013-08-27T05:01:20.704Z\'),"u":27,"d":27},"d-du":{"l0":"5.49","l1":"5.74","l2":"5.12"},"d-dw":{"c":2,"dc":2,"r":1,"dr":1}}',
                   "{\"a\":5,\"b\":6,\"ndm-ndls\":{\"r\":0,\"d\":0},\"ndm-ndss\":{\"r\":0,\"d\":0},\"d-d\":{\"s\":Date('2013-08-27T04:55:44.055Z'),\"u\":44,\"d\":44},\"d-du\":{\"l0\":\"7.83\",\"l1\":\"5.39\",\"l2\":\"4.80\"},\"d-dw\":{\"c\":0,\"dc\":0,\"r\":0,\"dr\":0}}",
                   "{\"s\":1.377579371994E9,\"a\":5,\"b\":6,\"ndm-ndls\":{\"r\":0,\"d\":0},\"ndm-ndss\":{\"r\":0,\"d\":0},\"d-d\":{\"s\":Date('2013-08-27T04:55:44.055Z'),\"u\":44,\"d\":44},\"d-du\":{\"l0\":\"7.83\",\"l1\":\"5.39\",\"l2\":\"4.80\"},\"d-dw\":{\"c\":0,\"dc\":0,\"r\":0,\"dr\":0}}",
                   ]){
	{
		var o = eval(s);
		if(o.a !== 5){
			throw new Error("Invalid value: a != 5, but: " + a);
		}
		if(o.b !== 6){
			throw new Error("Invalid value: b != 6, but: " + b);
		}
	}
	{
		var o = JSON.parse(s);
		if(o.a !== 5){
			throw new Error("Invalid value: a != 5, but: " + a);
		}
		if(o.b !== 6){
			throw new Error("Invalid value: b != 6, but: " + b);
		}
	}
}
out.println("done.");

out.print("this access...");
{
	function f(){
		return (= this.a[this.b] = this.c);
	}
	var o = {
		f : f,
		a : {
			key1 : 2206241
		},
		b : 'key1',
		c : 42
	}
	
	if(o.a.key1 != 2206241){
		throw "Invalid value already! expected 2206241, but: " + o.a.key1;
	}
	var buf;
	$output(buf){
		o.f();
	}
	if(o.a.key1 != 42){
		throw "Invalid value, expected 42, but: " + o.a.key1;
	}
	if(buf != '42'){
		throw "Invalid output value, expected '42', but: " + buf;
	}
}
out.println("done.");

out.print("this expressions...");
{
	function check2(){
		return this.aa + '/' + this.bb;
	}
	function check3(){
		return this.aa + '/' + this.bb + '-' + this.cc;
	}
	function check3a(){
		return this.aa + "/" + this.bb + "-" + this.cc.replace('+', ' plus ').replace('  ', ' ').replace(' ', '_') + ".txt";
	}
	function check3b(ace){
		var re = 'repl' + ace;
		return this.aa + "/" + this.bb + "-" + this.cc[re]('+', ' plus ')[re]('  ', ' ')[re](' ', '_') + ".text";
	}
	function check3c(ace){
		return this.aa + "/" + this.bb + "-" + this.cc['repl' + ace]('+', ' plus ')['repl' + ace]('  ', ' ')['repl' + ace](' ', '_') + ".textual";
	}
	var obj = {
		aa : '111',
		bb : '222',
		cc : '333',
		check2 : check2,
		check3 : check3,
		check3a : check3a,
		check3b : check3b,
		check3c : check3c,
	};
	
	var c2 = obj.check2();
	if(c2 != '111/222'){
		throw "OMG! c2 == " + c2;
	}
	
	var c3 = obj.check3();
	if(c3 != '111/222-333'){
		throw "OMG! c3 == " + c3;
	}
	var c3a = obj.check3a();
	if(c3a != '111/222-333.txt'){
		throw new Error("OMG! c3a == " + c3a);
	}
	var c3b = obj.check3b('ace');
	if(c3b != '111/222-333.text'){
		throw new Error("OMG! c3b == " + c3b);
	}
	var c3c = obj.check3c('ace');
	if(c3c != '111/222-333.textual'){
		throw new Error("OMG! c3c == " + c3c);
	}
}
out.println("done.");

out.print("hard access...");
{
	for each(var key in ['54',55,true]){
		var map5 = {};
		var map4 = {
			userStates : map5
		};
		var cUA = "zzz";
		var map3 = {
			zzz : map4
		};
		var map2 = {
			data : map3
		};
		var managerMap = {
			mmm : map2
		};
		var Record = {
			managerId : "mmm",
			userState : key
		};
		
		managerMap[Record.managerId].data[cUA].userStates[Record.userState] = 0;
		if(managerMap.mmm.data.zzz.userStates[key] !== 0){
			throw "value is not strictly 0 but: " + managerMap.mmm.data.zzz.userStates[key];
		}
		
		managerMap[Record.managerId].data[cUA].userStates[Record.userState] = managerMap[Record.managerId].data[cUA].userStates[Record.userState] + 1;
		if(managerMap.mmm.data.zzz.userStates[key] !== 1){
			throw "value is not strictly 1 but: " + managerMap.mmm.data.zzz.userStates[key];
		}
		
		managerMap[Record.managerId].data[cUA].userStates[Record.userState] = managerMap[Record.managerId].data[cUA].userStates[Record.userState] + 1;
		if(managerMap.mmm.data.zzz.userStates[key] !== 2){
			throw "value is not strictly 2 but: " + managerMap.mmm.data.zzz.userStates[key];
		}
		
		var test = managerMap[Record.managerId].data[cUA].userStates;
		test[Record.userState] = test[Record.userState] + 1;
		if(managerMap.mmm.data.zzz.userStates[key] !== 3){
			throw "value is not strictly 3 but: " + managerMap.mmm.data.zzz.userStates[key];
		}
		managerMap[Record.managerId].data[cUA].userStates[Record.userState] += 2;
		if(managerMap.mmm.data.zzz.userStates[key] != 5){
			throw "value is not 5 but: " + managerMap.mmm.data.zzz.userStates[key];
		}
		
		managerMap[Record.managerId].data[cUA].userStates[Record.userState] ++;
		if(managerMap.mmm.data.zzz.userStates[key] != 6){
			throw "value is not 6 but: " + managerMap.mmm.data.zzz.userStates[key];
		}
		
		managerMap[Record.managerId].data[cUA].userStates[Record.userState] += Record.managerId;
		if(managerMap.mmm.data.zzz.userStates[key] != '6mmm'){
			throw "value is not '6mmm' but: " + managerMap.mmm.data.zzz.userStates[key];
		}
	}
}
{
	var fo = function(){
		return {
			aa : 56
		};
	};
	if(fo()['aa'] != 56){
		throw "Must be 56!";
	}
	var fa = function(){
		return 'aa';
	};
	if(fo()[fa()] != 56){
		throw "Must be 56!";
	}
}
{
	var map = { aa : 56 };
	/**
	 * gonna use to complicate map access
	 */
	var fo = function(){
		return map;
	};
	if(fo() != map){
		throw "Must return map!";
	}
	if(map.aa != 56){
		throw "Must be 56";
	}
	fo().aa ++;
	if(map.aa != 57){
		throw "Must be 57";
	}
	fo().aa --;
	if(map.aa != 56){
		throw "Must be 56";
	}
	fo().aa += 1;
	if(map.aa != 57){
		throw "Must be 57";
	}
	fo().aa -= 1;
	if(map.aa != 56){
		throw "Must be 56";
	}
	/**
	 * gonna use to complicate property access
	 */
	var fa = function(){
		return 'aa';
	};
	fo()[fa()] ++;
	if(map.aa != 57){
		throw "Must be 57";
	}
	fo()[fa()] --;
	if(map.aa != 56){
		throw "Must be 56";
	}
	fo()[fa()] += 1;
	if(map.aa != 57){
		throw "Must be 57";
	}
	fo()[fa()] -= 1;
	if(map.aa != 56){
		throw "Must be 56";
	}
	/**
	 * gonna use to complicate increment/decrement operand
	 */
	var fn = function(){
		return 1;
	};
	fo()[fa()] += fn();
	if(map.aa != 57){
		throw "Must be 57";
	}
	fo()[fa()] -= fn();
	if(map.aa != 56){
		throw "Must be 56";
	}
}
out.println("done.");

out.print("function nested...");
{
	function zzz(b,c){
		function ff(a,d){
			return 1 + a - d + "aaa";
		}
		return ff(1 + b, c * 2) + ff(1 + b, c * 2);
	}

	function fff(aa){
		var fff = 5;
		return fff + aa;
	}
	if( fff(1) + fff(1) + zzz(2,7) != "12-10aaa-10aaa"){
		throw;
	}
}
out.println("done.");

out.print("WHILE test (1)...");
{
	var parent = 1;
	while(parent){
		parent = parent + 1;
		if(parent == 3){ 
			break;
		}
		if(parent == 5){
			throw "while break doesn't work!";
		}
	}
}
{
	var parent = 1;
	while(parent){
		var locals = 'z';
		parent = parent + 1;
		if(parent == 3){ 
			break;
		}
		if(parent == 5){
			throw "while break doesn't work!";
		}
	}
}
out.println("done.");

out.print("WHILE test (2)...");
{
	var parent = 1;
	while(true){
		parent = parent + 1;
		if(parent == 2){
			continue;
		}
		if(parent == 3){ 
			break;
		}
		if(parent == 5){
			throw "while-true break doesn't work!";
		}
	}
}
{
	var parent = 1;
	while(true){
		var locals = 'z';
		parent = parent + 1;
		if(parent == 2){
			continue;
		}
		if(parent == 3){ 
			break;
		}
		if(parent == 5){
			throw "while-true break doesn't work!";
		}
	}
}
out.println("done.");

out.print("WHILE test (3)...");
{
	{
		var truewhile = 0;
		while(true){
			if(truewhile ++ > 100) break;
			if(truewhile > 200) throw;
		}
	}
	var more = 5;
	var sum = 0;
	while(more){
		sum += more--;
	}
	while(sum){
		var a = sum - 1;
		sum = a;
	}

	if( sum ){
		throw;
	}

	while(true){
		break;
	}
	
	var z;
	
	do{
		z = 66;
	}while(false);
	
	if( z != 66 ){
		throw;
	}
	
	do{
		sum = sum + 1;
	}while(z != 66 || z == 12 || z == 11);
	
	if( z != 66 ){
		throw;
	}
	
	do{
		sum = sum + 1;
		break;
	}while(z != 66 || z == 12 || z == 11);
	
	if( z != 66 ){
		throw;
	}
}
{
	var zz = 14, next = true;
	do{
		if(zz == 15) {
			next = false;
		}
		++zz;
	}while(next);
	
	if( zz != 16 ){
		throw;
	}
}
{
	var zz = 14, next = true, t;
	try{
		do{
			if(zz == 15) {
				next = false;
			}
			throw "check";
			++zz;
		}while(next);
	}catch(e){
		t = e;
	}
	
	if( t != "check" ){
		throw t;
	}
}
{
	var zz = 14, next = true, t;
	try{
		do{
			if(zz == 15) {
				next = false;
				continue;
			}
			throw "check";
			++zz;
		}while(next);
	}catch(e){
		t = e;
	}
	
	if( t != "check" ){
		throw t;
	}
}
{
	var zz = 14, next = true, t;
	try{
		do{
			if(zz == 15) {
				next = false;
				break;
			}
			throw "check";
			++zz;
		}while(next);
	}catch(e){
		t = e;
	}
	
	if( t != "check" ){
		throw t;
	}
}
{
	var zz = 14, next = true, t;
	try{
		do{
			if(zz == 11) {
				next = false;
				break;
			}
			throw "check";
			continue;
			++zz;
		}while(next);
	}catch(e){
		t = e;
	}
	
	if( t != "check" ){
		throw t;
	}
}
out.println("done.");

out.print("WHILE test (4)...");
{
	var parent = 1;
	while(++parent >= 0){
		parent = parent + 1;
		if(parent == 2){
			continue;
		}
		if(parent == 3){ 
			break;
		}
		if(parent == 5){
			throw "while-true break doesn't work!";
		}
	}
}
out.println("done.");

out.print("WHILE test (5)...");
{
	var parent = 10, check = 10;
	while(--parent >= 0){
		check = check - 1;
		if(check != parent){
			throw "expected to be equal: " + parent + " != " + check;
		}
		if(parent == 2){
			continue;
		}
		if(parent < 0){ 
			break;
		}
		if(parent < 0){
			throw "while-true break doesn't work!";
		}
	}
}
out.println("done.");

out.print("BOOLEAN check tests...");
{
	if(!Boolean.prototype){
		throw "no Boolean.prototype accessible!";
	}
	{
		var result;
		var boolean = true;
		result = boolean.toString();
		if(result !== 'true'){
			throw "Boolean.prototype.toString(), expected 'true', but: " + result;
		}
	}
	{
		var result;
		var boolean = false;
		result = boolean.toString();
		if(result !== 'false'){
			throw "Boolean.prototype.toString(), expected 'false', but: " + result;
		}
	}
}
out.println("done.");

out.print("Exception in constructor check tests...");
{
	{
		function Test(){
			null.a();
		}
		
		try{
			new Test();
			throw "Shouldn't get here!";
		}catch(e){
			//
		}
	}
	{
		function Test(){
			throw new Error("F1");
		}
		
		try{
			new Test();
			throw "Shouldn't get here!";
		}catch(e){
			//
		}
	}
	{
		function Test(){
			throw "F2";
		}
		
		try{
			new Test();
			throw "Shouldn't get here!";
		}catch(e){
			if(e != 'F2'){
				throw "Invalid exception value: expected=F2, result=" + e;
			}
		}
	}
}
out.println("done.");

out.print("NUMBER check tests...");
{
	if(!Number.prototype){
		throw "no Number.prototype accessible!";
	}
	{
		var result;
		var number = 32;
		result = number.toString();
		if(result !== '32'){
			throw "Number.prototype.toString(), expected '32', but: " + result;
		}
	}
	{
		var result;
		var number = 32;
		result = number.toString(16);
		if(result !== '20'){
			throw "Number.prototype.toString(16), expected '20', but: " + result;
		}
	}
	{
		var result;
		var number = 8;
		result = number.toString(8);
		if(result !== '10'){
			throw "Number.prototype.toString(8), expected '10', but: " + result;
		}
	}
	{
		/**
		 * toFixed test
		 */
		var number = 3.14;
		if(number != 3.14){
			throws "Something really is broken here!";
		}
		{
			var result = number.toFixed();
			if(result !== "3"){
				throw "Number.prototype.toFixed(), result=" + result;
			}
		}
		{
			var result = number.toFixed(0);
			if(result != "3"){
				throw "Number.prototype.toFixed(0), result=" + result;
			}
		}
		{
			var result = number.toFixed(1);
			if(result != "3.1"){
				throw "Number.prototype.toFixed(1), result=" + result;
			}
		}
		{
			var result = number.toFixed(3);
			if(result != "3.140"){
				throw "Number.prototype.toFixed(3), result=" + result;
			}
		}
	}
	{
		/**
		 * toPrecision test
		 */
		var number = 3.14;
		if(number != 3.14){
			throws "Something really is broken here!";
		}
		{
			var result = number.toPrecision();
			if(result !== "3.14"){
				throw "Number.prototype.toPrecision(), result=" + result;
			}
		}
		{
			var result = number.toPrecision(1);
			if(result != "3"){
				throw "Number.prototype.toPrecision(1), result=" + result;
			}
		}
		{
			var result = number.toPrecision(2);
			if(result != "3.1"){
				throw "Number.prototype.toPrecision(2), result=" + result;
			}
		}
		{
			var result = number.toPrecision(3);
			if(result != "3.14"){
				throw "Number.prototype.toPrecision(3), result=" + result;
			}
		}
		{
			var result = number.toPrecision(5);
			if(result != "3.14"){
				throw "Number.prototype.toPrecision(5), result=" + result;
			}
		}
	}
	{
		var number = 1.377580019811E9;
		if(number != 1.377580019811E9){
			throw new Error("Not equals: " + number);
		}
		if(number === undefined){
			throw new Error("Number parsing is broken for long floating decimals like: 1.377580019811E9");
		}
	}
	for(var check in ['.76', '0.76', '7.6E-1', '0.076E1', 0.76]){
		var number = Number(check);
		if(number != 0.76){
			throw new Error("Not equals: " + number + ", check: " + check);
		}
	}
}
out.println("done.");

out.print("STRING check tests...");
{
	if(!String.prototype){
		throw "no String.prototype accessible!";
	}
	{
		var result;
		result = "123".length;
		if(result != 3){
			throw "String.prototype.length, expected 3, but: " + result;
		}
		var string = '321';
		result = string.length;
		if(result != 3){
			throw "String.prototype.length, expected 3, but: " + result;
		}
	}
	{
		var result;
		result = "123".length();
		if(result != 3){
			throw "String.prototype.length(), expected 3, but: " + result;
		}
		var string = '321';
		result = string.length();
		if(result != 3){
			throw "String.prototype.length(), expected 3, but: " + result;
		}
	}
	{
		var result;
		result = "123".charAt(1);
		if(result != '2'){
			throw "String.prototype.charAt, expected '2', but: " + result;
		}
		var string = '321';
		result = string.charAt(1);
		if(result != '2'){
			throw "String.prototype.charAt, expected '2', but: " + result;
		}
	}
	{
		/**
		 * array-like access (non standard for EcmaScript but used in JavaScript)
		 */
		var result;
		result = "123"[1];
		if(result != '2'){
			throw "String array access, expected '2', but: " + result;
		}
		var string = '321';
		result = string[1];
		if(result != '2'){
			throw "String array access, expected '2', but: " + result;
		}
	}
	{
		var result;
		result = "1 3".charCodeAt(1);
		if(result != 32){
			throw "String.prototype.charCodeAt, expected 32, but: " + result;
		}
		var string = '3 1';
		result = string.charCodeAt(1);
		if(result != 32){
			throw "String.prototype.charCodeAt, expected 32, but: " + result;
		}
	}
	{
		var result;
		result = "AbC".toUpperCase();
		if(result != 'ABC'){
			throw "String.prototype.toUpperCase, expected 'ABC', but: " + result;
		}
		var string = 'CbA';
		result = string.toUpperCase();
		if(result != 'CBA'){
			throw "String.prototype.toUpperCase, expected 'CBA', but: " + result;
		}
	}
	{
		var result;
		result = "AbC".toLowerCase();
		if(result != 'abc'){
			throw "String.prototype.toLowerCase, expected 'abc', but: " + result;
		}
		var string = 'CbA';
		result = string.toLowerCase();
		if(result != 'cba'){
			throw "String.prototype.toLowerCase, expected 'cba', but: " + result;
		}
	}
	{
		var result;
		result = "Abc".toString();
		if(result != 'Abc'){
			throw "String.prototype.toString, expected 'Abc', but: " + result;
		}
		var string = 'Cba';
		result = string.toString();
		if(result != 'Cba'){
			throw "String.prototype.toString, expected 'Cba', but: " + result;
		}
	}
	{
		var result = String.fromCharCode(65,66,67);
		if(result != 'ABC'){
			throw "String.fromCharCode: expected 'ABC', but: " + result;
		}
	}
	{
		/**
		 * substring
		 */
		var r;
		r = "ABCDEF".substring(0, 3);
		if(r != 'ABC'){
			throw;
		}
		r = "ABCDEF".substring(3, 6);
		if(r != 'DEF'){
			throw;
		}
		r = "ABCDEF".substring(0, 6);
		if(r != 'ABCDEF'){
			throw;
		}
		r = "ABCDEF".substring(0, 10);
		if(r != 'ABCDEF'){
			throw;
		}
	}
	{
		/**
		 * substr
		 */
		var r;
		r = "ABCDEF".substr(0, 3);
		if(r != 'ABC'){
			throw;
		}
		r = "ABCDEF".substr(-3);
		if(r != 'DEF'){
			throw;
		}
		r = "ABCDEF".substr(3, 3);
		if(r != 'DEF'){
			throw;
		}
		r = "ABCDEF".substr(0, 6);
		if(r != 'ABCDEF'){
			throw;
		}
		r = "ABCDEF".substr(0, 10);
		if(r != 'ABCDEF'){
			throw;
		}
	}
	{
		/**
		 * slice
		 */
		var r;
		r = "ABCDEF".slice(0, 3);
		if(r != 'ABC'){
			throw;
		}
		r = "ABCDEF".slice(3);
		if(r != 'DEF'){
			throw;
		}
		r = "ABCDEF".slice(-3);
		if(r != 'DEF'){
			throw;
		}
		r = "ABCDEF".slice(3, 6);
		if(r != 'DEF'){
			throw;
		}
		r = "ABCDEF".slice(0, 6);
		if(r != 'ABCDEF'){
			throw;
		}
		r = "ABCDEF".slice(0, 10);
		if(r != 'ABCDEF'){
			throw;
		}
	}
	{
		var c = "1234567".replace(/\D/g, "").length == 7;
		var d = "1234567".replace(/\D/g, "").length === 7;
		var a = {
			a : "1234567".replace(/\D/g, ""),
			b : "1234567".replace(/\D/g, "").length,
			c : "1234567".replace(/\D/g, "").length == 7,
			d : "1234567".replace(/\D/g, "").length === 7,
		};
		if(!c || !d){
			throw new Error("Direct?");
		}
	}
}
out.println("done.");

out.print("OBJECT check tests...");
{
	if(!Object.prototype){
		throw "no Object.prototype accessible!";
	}
	{
		if(typeof Object.hasOwnProperty != 'function'){
			throw "Oops, method expected!";
		}
		var o = { aa : 2206241 };
		if(o.hasOwnProperty('bb')){
			throw "Object should not have property 'bb'";
		}
		if(!o.hasOwnProperty('aa')){
			throw "Object should have property 'aa'";
		}
	}
	{
		if(typeof Object.prototype.isPrototypeOf != 'function'){
			throw "Oops, method expected!";
		}
		if(typeof Boolean.prototype.isPrototypeOf != 'function'){
			throw "Oops, method expected!";
		}
		var o = { aa : 2206241 };
		if(Boolean.prototype.isPrototypeOf(o)){
			throw "Boolean.prototype should not be prototype of object";
		}
		if(!Object.prototype.isPrototypeOf(o)){
			throw "Object.prototype should be prototype of object";
		}
	}
	{
		if(typeof Object.propertyIsEnumerable != 'function'){
			throw "Oops, method expected!";
		}
		var o = { aa : 2206241 };
		if(o.propertyIsEnumerable('bb')){
			throw "Object should not have property 'bb'";
		}
		if(!o.propertyIsEnumerable('aa')){
			throw "Object should have property 'aa'";
		}
	}
	{
		var arr = Object.keys({a:1,b:2});
		if(arr.length != 2){
			throw "Expected to be 2, but: " + arr.length;
		}
		if(arr[0] != 'a'){
			throw "Expected to be 'a', but: " + arr[0];
		}
		if(arr[1] != 'b'){
			throw "Expected to be 'b', but: " + arr[1];
		}
	}
	{
		//
		// Object.defineProperty
		//
		if(typeof Object.defineProperty != 'function'){
			throw "Oops, method expected!";
		}

		var o = {};
		Object.defineProperty(o, "a", { value : 1, enumerable:true });
		Object.defineProperty(o, "b", { value : 2, enumerable:false });
		Object.defineProperty(o, "c", { value : 3 }); // enumerable defaults to false
		o.d = 4;

		var s = '';
		for (var i in o) {    
			s += i + ":" + o[i] + "::";
		}	
		if(s != 'a:1::d:4::'){
			throw "Expected to be 'a:1::d:4::', but: " + s;
		}
	}
}
out.println("done.");

out.print("ARRAY check tests...");
{
	if(!Array){
		throw "no global Array object accessible!";
	}
	if(!Array.isArray){
		throw "no Array.isArray accessible!";
	}
	if(!Array.prototype){
		throw "no Array.prototype accessible!";
	}
	if(!Array.prototype.pop){
		throw "Array.prototype.pop not defined!";
	}
	if(!Array.prototype.push){
		throw "Array.prototype.push not defined!";
	}
	if(!Array.prototype.shift){
		throw "Array.prototype.push not defined!";
	}
	if(!(new Array())){
		throw "(new Array()) is not defined!";
	}
	if((new Array()).length === undefined){
		throw "(new Array()).length is not defined!";
	}
	if(!(new Array("test"))){
		throw "(new Array(\"test\")) is not defined!";
	}
	if((new Array("test")).length != 1){
		throw "(new Array(\"test\")).length is not == 1!";
	}
	if(!(new Array()).push){
		throw "(new Array()).push is not defined!";
	}
	
	{
		var arr = [];
		if(arr.length != 0) throw "Not zero: " + arr.length;
	}
	{
		var arr = [new Array()];
		if(arr.length != 1) throw "Not one: " + arr.length;
	}
	{
		var arr = [new Array(), new Array()];
		if(arr.length != 2) throw "Not two: " + arr.length;
	}
	
	{
		// MDC tests
		var a = ["Wind","Rain","Fire"];
		var myVar1 = a.join();      // assigns "Wind,Rain,Fire" to myVar1
		if(myVar1 != "Wind,Rain,Fire"){
			throw "Incorrect: '" + myVar1 + "'!";
		}
		var myVar2 = a.join(", ");  // assigns "Wind, Rain, Fire" to myVar2
		if(myVar2 != "Wind, Rain, Fire"){
			throw "Incorrect: '" + myVar2 + "'!";
		}
		var myVar3 = a.join(" + "); // assigns "Wind + Rain + Fire" to myVar3
		if(myVar3 != "Wind + Rain + Fire"){
			throw "Incorrect: '" + myVar3 + "'!";
		}
	}
	{
		// MDC tests
		var a = new Array("Wind","Rain","Fire");
		var myVar1 = a.join();      // assigns "Wind,Rain,Fire" to myVar1
		if(myVar1 != "Wind,Rain,Fire"){
			throw "Incorrect: '" + myVar1 + "'!";
		}
		var myVar2 = a.join(", ");  // assigns "Wind, Rain, Fire" to myVar2
		if(myVar2 != "Wind, Rain, Fire"){
			throw "Incorrect: '" + myVar2 + "'!";
		}
		var myVar3 = a.join(" + "); // assigns "Wind + Rain + Fire" to myVar3
		if(myVar3 != "Wind + Rain + Fire"){
			throw "Incorrect: '" + myVar3 + "'!";
		}
	}

	{
		
		if(!Array.isArray([])){
			throw "Array.isArray doesn't recognise an empty array literal!";
		}
		
		if(!Array.isArray([1, 2])){
			throw "Array.isArray doesn't recognise an array literal!";
		}
		
		// isArray test
		function test(fun, expect) { 
			if (fun() !== expect) throw "Array.isArray: FAIL: " + fun + ", expect: " + expect; 
		}
		
		test(function() { 
			return Array.isArray([]); 
		}, true);
		
		test(function() { return Array.isArray([1,2,"",null,'z',99.1]); }, true);
		test(function() { return Array.isArray(new Array()); }, true);
		test(function() { return Array.isArray(); }, false);
		test(function() { return Array.isArray({ constructor: Array }); }, false);
		test(function() { return Array.isArray({ push: Array.prototype.push, concat: Array.prototype.concat }); }, false);
		test(function() { return Array.isArray(17); }, false);
		test(function() { return Array.isArray({toString : function() { return "[object Array]"; }}); }, false);
		test(function() { return Array.isArray({ __proto__: Array.prototype }); }, false);
		test(function() { return Array.isArray({ length: 0 }); }, false);
		
		{
			var array = {
				constructor: Array,
				__proto__: Array.prototype,
				push: Array.prototype.push, 
				pop: Array.prototype.pop, 
				concat: Array.prototype.concat,
			};
			Object.defineProperty(array, "length", {
				value : 0,
				writable : true,
				enumerable : false,
				configurable : false
			});
			test(function() { return Array.isArray(array); }, false);
		}
	}
	
	{
		// shift
		var a = [3, 4, 5, 6];
		var shifted = a.shift()
		if(shifted != 3){
			throw "Must be 3!";
		}
		if(a[0] != 4){
			throw "Must be 4!";
		}
	}
	{
		// unshift
		var a = [5, 6];
		a.unshift(4);
		a.unshift(2,3);
		if(a.join('') != '23456'){
			throw "unshift fail: " + a.join('');
		}
	}
	{
		// concat
		var a = [2, 3, 4, 5];
		var b = [6, 7];
		var c = 8;
		var result = a.concat(b, c, 9)
		if(result.join('') != '23456789'){
			throw "Must be '23456789'!";
		}
		var resul2 = [].concat("Hello!");
		if(resul2.length != 1){
			throw "Length is not 1: " + resul2.length;
		}
	}
	
	{
		var array = [];
		if(array.length != 0){
			throw "length is: " + array.length;
		}
		array[0] = '';
		if(array.length != 1){
			throw "length is: " + array.length;
		}
		array.push('');
		if(array.length != 2){
			throw "length is: " + array.length;
		}
		array.add('');
		if(array.length != 3){
			throw "length is: " + array.length;
		}
		if(array.length != array.size()){
			throw "size function extension is not working!";
		}
	}
	
	{
		var arr = ['a','b','c'];
		arr['2'] = 'z';
		var rez = arr[0] + '-' + arr['1'] + '-' + arr[2];
		if( rez != 'a-b-z' ){
			throw '"a-b-z" != ' + rez;
		}
	}

	{
		var arr = ['a','b',];
		arr['2'] = 'x';
		var rez = arr[0] + '-' + arr['1'] + '-' + arr[2];
		if( rez != 'a-b-x' ){
			throw '"a-b-x" != ' + rez;
		}
	}

	{
		var arr = ['a',];
		arr[1] = 'p';
		arr['2'] = 'x';
		var rez = arr[0] + '-' + arr['1'] + '-' + arr[2];
		if( rez != 'a-p-x' ){
			throw '"a-p-x" != ' + rez;
		}
	}


	/**
	 {
	 	var arr = ['a', , , ,];
	 	if( arr.length != 4 ){
	 		throw "arr.length != 4 != " + arr.length;
	 	}
	 }
	{
		var arr = ['a', , , ,'z'];
		if( arr.length != 5 ){
			throw "arr.length != 5 != " + arr.length;
		}
	}
	**/

	if(Array.prototype.addd){
		throw "Array.prototype.addd defined!";
	}
	Array.prototype.addd = Array.prototype.push;
	if(!Array.prototype.addd){
		throw "Array.prototype.addd not defined!";
	}
	{
		import java.util.ArrayList;
		var list = new ArrayList(10);
		if(list.length != 0){
			throw "should have length property equal to 0, but: " + list.length;
		}
		list.addd(10);
		if(list.length != 1){
			throw "should have length property equal to 1, but: " + list.length;
		}
		if(!list.push){
			throw "should have 'push' property!";
		}
	}
	{
		Array.prototype.addd = undefined;
	}
	{
		// extras
		var array = ['aa','bb','cc','dd'];
		if(array.isEmpty()){
			throw 'array should not be empty, its size: ' + array.length;
		}
		if(array.size() != array.length){
			throw "array size doesn't match its length: length=" + array.length + ", size=" + array.size();
		}
		if(!array.includes('bb')){
			throw 'array includes should be able to find "bb"';
		}
		if(array.includes('ab')){
			throw 'array includes should not be able to find "ab"';
		}
		if(!array.contains('bb')){
			throw 'array contains should be able to find "bb"';
		}
		if(array.contains('ab')){
			throw 'array contains should not be able to find "ab"';
		}
		if(array.subList(2).length != 2){
			throw 'array subList(f) length is: ' + array.subList(2).length;
		}
		if(array.subList(1,3).length != 2){
			throw 'array subList(f,t) length is: ' + array.subList(1,2).length;
		}
		if(!array.subList(1,3).contains('bb')){
			throw 'array (subList) contains should be able to find "bb"';
		}
		if(array.subList(1,3).contains('aa')){
			throw 'array (subList) contains should not be able to find "aa"';
		}
	}
	{
		/**
		 * Out of array should expand!
		 */
		var arr = [];
		arr[5] = 5;
		if(arr[5] != 5){
			throw "Expected to be 5, but: " + arr[5];
		}
		if(arr[4] !== undefined){
			throw "Expected to be undefined, but: " + arr[4];
		}
		/**
		 * Out of array should not fail
		 */
		if(arr[9] !== undefined){
			throw "Expected to be undefined, but: " + arr[9];
		}
	}
	{
		var array = [1,2,3];
		{
			var text = array.pop();
			if(text != '3'){
				throw "Expected to be '3' but: " + text;
			}
		}
		{
			var text = array.pop();
			if(text != '2'){
				throw "Expected to be '2' but: " + text;
			}
		}
		{
			var text = array.pop();
			if(text != '1'){
				throw "Expected to be '1' but: " + text;
			}
		}
		{
			var text = array.pop();
			if(text !== undefined){
				throw "Expected to be undefined but: " + text;
			}
		}
	}
	{
		/**
		 * reduce
		 */
		var data = [10,1,2,3,4,5,6,7,8,9].reduce(function(previousValue, currentValue, index, array){
			return previousValue + currentValue;
		});
		if(data != 55) throw "reduce error: " + data;
	}
	{
		/**
		 * reduce
		 */
		var data = [0,1,2,3,4,5,6,7,8,9].reduce(function(previousValue, currentValue, index, array){
			previousValue.sum += currentValue;
			return previousValue;
		}, {sum:0});
		if(data.sum != 45) throw "reduce error: " + data.sum;
	}
}
{
	/**
	 * Array convertor
	 */
	{
		var a = [];
		if(Array(a) !== a){
			throw "Already an array!";
		}
	}
	{
		var a = '123';
		var r = Array( a );
		if(r.length != 1){
			throw "expected to have length == 1, but: " + r.length;
		}
		if(r[0] !== a){
			throw "the only element should be " + a + ", but: " + r[0];
		}
	}
	{
		var a = 42;
		var r = Array( a );
		if(r.length != 42){
			throw "expected to have length == 42, but: " + r.length;
		}
		if(r[0] !== undefined){
			throw "the only element should be undefined, but: " + r[0];
		}
	}
	{
		var a = 42;
		var r = Array( a, 12 );
		if(r.length != 2){
			throw "expected to have length == 2, but: " + r.length;
		}
		if(r[0] !== a){
			throw "the only element should be " + a + ", but: " + r[0];
		}
	}
}
{
	/**
	 * Generic 'push'
	 */
	var o = {};
	if(o.length){
		throw "Should not be defined for an object";
	}
	Array.prototype.push.call(o, '1');
	Array.prototype.push.call(o, '2');
	Array.prototype.push.call(o, '3');
	if(o.length != 3){
		throw "Length should be 3, but: " + o.length;
	}
	if(o[0] != 1){
		throw "Element 0 should be 1, but: " + o[i];
	}
	for(var i = 0; i < 3; i++){
		if(o[i] != i + 1){
			throw "Element "+i+" should be "+(i+1)+", but: " + o[i];
		}
	}
}
{
	/**
	 * Generic 'pop'
	 */
	var o = {};
	Array.prototype.push.call(o, '1');
	Array.prototype.push.call(o, '2');
	Array.prototype.push.call(o, '3');
	{
		var text = Array.prototype.pop.call(o);
		if(text != '3'){
			throw "Expected to be '3' but: " + text;
		}
	}
	{
		var text = Array.prototype.pop.call(o);
		if(text != '2'){
			throw "Expected to be '2' but: " + text;
		}
	}
	{
		var text = Array.prototype.pop.call(o);
		if(text != '1'){
			throw "Expected to be '1' but: " + text;
		}
	}
	{
		var text = Array.prototype.pop.call(o);
		if(text !== undefined){
			throw "Expected to be undefined but: " + text;
		}
	}
	{
		/**
		 * 15.4.4.16 Array.prototype.every ( callbackfn [ , thisArg ] )
		 */
		var ot = '', it = '', at = '';
		
		function test(o,i,a){
			ot += o;
			it += i;
			at += a.join('');
			return i < 3;
		}
		
		if(![].every(test)) throw "Should return true for an empty array!";
			
		if(![1,2,3].every(test)) throw "Should return true when all iterations return true!";
		
		if(ot != '123') throw "";
		if(it != '012') throw "";
		if(at != '123123123') throw "";
		
		if([1,2,3,4,5,6,7].every(test)) throw "Should return false and stop when iteration returns false!";

		if(ot != '1231234') throw "";
		if(it != '0120123') throw "";
	}
	{
		/**
		 * 15.4.4.17 Array.prototype.some ( callbackfn [ , thisArg ] )
		 */
		var ot = '', it = '', at = '';
		
		function test(o,i,a){
			ot += o;
			it += i;
			at += a.join('');
			return i > 2;
		}
		
		if([].some(test)) throw "Should return false for an empty array!";
			
		if([1,2,3].some(test)) throw "Should return false when all iterations return false!";
		
		if(ot != '123') throw "";
		if(it != '012') throw "";
		if(at != '123123123') throw "";
		
		if(![1,2,3,4,5,6,7].some(test)) throw "Should return true and stop when iteration returns true!";

		if(ot != '1231234') throw "";
		if(it != '0120123') throw "";
	}
	{
		/**
		 * 15.4.4.18 Array.prototype.forEach ( callbackfn [ , thisArg ] )
		 */
		var ot = '', it = '', at = '';
		
		function test(o,i,a){
			ot += o;
			it += i;
			at += a.join('');
		}
		
		[1,2,3].forEach(test);
		
		if(ot != '123') throw "";
		if(it != '012') throw "";
		if(at != '123123123') throw "";
	}
}
{
	/**
	 * Generic 'join'
	 */
	var o = {};
	Array.prototype.push.call(o, '1');
	Array.prototype.push.call(o, '2');
	Array.prototype.push.call(o, '3');
	var text = Array.prototype.join.call(o, '');
	if(text != '123'){
		throw "Expected to be '123' but: " + text;
	}
}
{
	/**
	 * Array.prototype.sort default
	 */
	var arr = [13,11,14];
	var c;
	c = arr.join('');
	if("131114" != c) throw "'131114' != " + c + ", " + Format.jsDescribe(c);
	arr.sort();
	c = arr.join('');
	if("111314" != c) throw "'111314' != " + c + ", array: " + Format.jsDescribe(arr);
}
{
	/**
	 * Array.prototype.sort callback
	 */
	var arr = [23,21,24];
	var c;
	c = arr.join('');
	if("232124" != c) throw "'232124' != " + c + ", " + Format.jsDescribe(c);
	arr.sort(function(a,b){return a-b;});
	c = arr.join('');
	if("212324" != c) throw "'212324' != " + c + ", array: " + Format.jsDescribe(arr);
}
{
	/**
	 * Array.prototype.sort callback again (backwards)
	 */
	var arr = [33,31,34];
	var c;
	c = arr.join('');
	if("333134" != c) throw "'333134' != " + c + ", " + Format.jsDescribe(c);
	arr.sort(function(a,b){return b-a;});
	c = arr.join('');
	if("343331" != c) throw "'343331' != " + c + ", array: " + Format.jsDescribe(arr);
}
{
	/**
	 * slice
	 */
	var r;
	r = ['A','B','C','D','E','F'].slice(0, 3).join('');
	if(r != 'ABC'){
		throw;
	}
	r = ['A','B','C','D','E','F'].slice(3).join('');
	if(r != 'DEF'){
		throw;
	}
	r = ['A','B','C','D','E','F'].slice(-3).join('');
	if(r != 'DEF'){
		throw;
	}
	r = ['A','B','C','D','E','F'].slice(3, 6).join('');
	if(r != 'DEF'){
		throw;
	}
	r = ['A','B','C','D','E','F'].slice(0, 6).join('');
	if(r != 'ABCDEF'){
		throw;
	}
	r = ['A','B','C','D','E','F'].slice(0, 10).join('');
	if(r != 'ABCDEF'){
		throw;
	}
}
out.println("done.");

out.print("DATE check tests...");
{
	if(!Date){
		throw "no global Date object accessible!";
	}
	if(!Date.prototype){
		throw "no Date.prototype accessible!";
	}
	if(!Date.prototype.getTime){
		throw "Date.prototype.getTime not defined, Date.prototype: " + Format.jsDescribe(Date.prototype) + ", keys:" + (Date.prototype.keySet());
	}
	if(typeof Date() != "string"){
		throw "Result of Date function should be string, but: " + (typeof Date());
	}
	if(!(new Date())){
		throw "(new Date()) is not defined!";
	}
	if(!(new Date(0))){
		throw "(new Date(0)) is not defined!";
	}
	if(!(new Date(2206241))){
		throw "(new Date(2206241)) is not defined!";
	}
	if(!(new Date(2206241)).getTime){
		throw "(new Date(2206241)).getTime is not defined!";
	}
	if((new Date(2206241)).getTime() != 2206241){
		throw "(new Date(2206241)).getTime() != 2206241, but: " + (new Date(2206241)).getTime();
	}
}
{
	/**
	 * compare
	 */
	{
		/**
		 * equals
		 */
		var date1 = new Date(5913621);
		var date2 = new Date(5913621);
		if(date1 == date2){
			/**
			 * sorry... thats the rules %(
			 */
			/**
			 * Oops, i've changed my mind - some old scripts do break. 
			 * 
			 * FIXME: PrimitiveDate, while 'new Date()' must return non-primitive BaseDateObject
			 */
			// throw "Date.prototype.compare on '=='";
		}
		if(date1 > date2){
			throw "Date.prototype.compare on '>'";
		}
		if(date2 > date1){
			throw "Date.prototype.compare on '>'";
		}
		if(date1 < date2){
			throw "Date.prototype.compare on '<'";
		}
		if(date2 < date1){
			throw "Date.prototype.compare on '<'";
		}
	}
	{
		/**
		 * not equals
		 */
		var date1 = new Date(4218410);
		var date2 = new Date(5913621);
		if(date1 == date2){
			throw "Date.prototype.compare on '=='";
		}
		if(date1 > date2){
			throw "Date.prototype.compare on '>'";
		}
		if(date2 < date1){
			throw "Date.prototype.compare on '<'";
		}
		if(date1 >= date2){
			throw "Date.prototype.compare on '>='";
		}
		if(date2 <= date1){
			throw "Date.prototype.compare on '<='";
		}
	}
}
{
	/**
	 * arithmetics
	 */
	for each(var date in [
	                      new Date(0), 
	                      new Date(2206241)
	                      ]) {
		{
			var result1 = date - 4255397;
			var result2 = date.getTime() - 4255397;
			if(result1 != result2){
				throw "date (" + date + ") - number: " + result1 + " != " +result2;
			}
			var result3 = date - (new Date(4255397));
			if(result3 != result1){
				throw "date (" + date + ") - date: " + result1 + " != " +result2;
			}
		}
		{
			var result1 = date + 4263718;
			var result2 = date.getTime() + 4263718;
			if(result1 != result2){
				throw "date (" + date + ") + number: " + result1 + " != " +result2;
			}
			var result3 = date + (new Date(4263718));
			if(result3 != result1){
				throw "date (" + date + ") + date: " + result1 + " != " +result2;
			}
		}
	}
}
out.println("done.");

out.print("URL check tests...");
{ 
	if(!URL){
		throw "no URL constructor accessible!";
	}
	if(!URL.prototype){
		throw "no URL.prototype accessible!";
	}
	
	{
		const url = URL.parse("434\n\n2434");
		if(url){
			throw "URL.parse must return null on invalid url, but: " + url;
		}
		var test = null;
		try{
			const url = new URL("434\n\n2434");
			test = false;
		}catch(e){
			test = true;
		}
		if(true !== test){
			throw "URL constructor must fail on invalid url, test: " + url;
		}
	}
	{
		const url = URL.parse("protocol://username:password@hostname:8080/path?search#hash");
		if(!url){
			throw "URL.parse must return url object on valid url, but: " + url;
		}
		if(url.href !== "protocol://username:password@hostname:8080/path?search#hash"){
			throw new Error("url.href: " + url.href);
		}
		
		if(url.protocol !== "protocol:"){
			throw new Error("url.protocol: " + url.protocol);
		}
		
		if(url.username !== "username"){
			throw new Error("url.username: " + url.username);
		}
		if(url.password !== "password"){
			throw new Error("url.password: " + url.password);
		}
		
		if(url.host !== "hostname:8080"){
			throw new Error("url.host: " + url.host);
		}
		if(url.hostname !== "hostname"){
			throw new Error("url.hostname: " + url.hostname);
		}
		if(url.port !== "8080"){
			throw new Error("url.port: " + url.port);
		}
		
		if(url.pathname !== "/path"){
			throw new Error("url.pathname: " + url.pathname);
		}
		if(url.path !== "/path"){
			throw new Error("url.path: " + url.path);
		}
		
		if(url.search !== "?search"){
			throw new Error("url.search: " + url.search);
		}
		if(url.query !== "search"){
			throw new Error("url.query: " + url.query);
		}
		
		if(url.hash !== "#hash"){
			throw new Error("url.hash: " + url.hash);
		}
	}
}
out.println("done.");

out.print("REGEXP check tests...");
{ 
	if(!RegExp){
		throw "no RegExp constructor accessible!";
	}
	if(!RegExp.prototype){
		throw "no RegExp.prototype accessible!";
	}
	if(!(/ABC\w+/g)){
		throw "Inline RegExp";
	}
	{
		var r = /\D/g;
		if("12345".replace(r, '').length != 5){
			throw new Error("5 != length: " + "12345".replace(r, ''));
		}
		if("1-2-3-4-5".replace(r, '').length != 5){
			throw new Error("5 != length: " + "1-2-3-4-5".replace(r, ''));
		}
		if(r.test("123") !== false){
			throw new Error("Inline RegExp: expected false");
		}
		if(r.test("1-3") !== true){
			throw new Error("Inline RegExp: expected true");
		}
		{
			var result = "123".split(r);
			if(!Array.isArray(result)){
				throw new Error("Supposed to be an array");
			}
			if(result.length != 1){
				throw new Error("Supposed to have 1 elements");
			}
			if(result[0] != '123'){
				throw new Error("First element supposed to be '123'");
			}
		}
		{
			var result = "1-3".split(r);
			if(!Array.isArray(result)){
				throw new Error("Supposed to be an array");
			}
			if(result.length != 2){
				throw new Error("Supposed to have 2 elements");
			}
			if(result[0] != '1'){
				throw new Error("First element supposed to be '1'");
			}
			if(result[1] != '3'){
				throw new Error("Second element supposed to be '3'");
			}
		}
		{
			var result = "123".search(r);
			if(result !== -1){
				throw new Error("Supposed to be -1, but: " + result);
			}
		}
		{
			var result = "1-3".search(r);
			if(result !== 1){
				throw new Error("Supposed to be 1, but: " + result);
			}
		}
		{
			var result = "123".match(r);
			if(result){
				throw new Error("Supposed not to match, but: " + result);
			}
		}
		{
			var result = "1-3".match(r);
			if(!result){
				throw new Error("Supposed to match, but: " + result);
			}
			if(result.length != 1){
				throw new Error("Supposed to have 1 element");
			}
			if(result[0] != '-'){
				throw new Error("First element supposed to be '-'");
			}
		}
		{
			var str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			var regexp = /[A-C]/gi;
			var matches_array = str.match(regexp);
			if(matches_array.join('') != 'ABCabc'){
				throw new Error("Oops - that's wrong!");
			}
		}
		/**
		 * from:<code>
		 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/RegExp/exec
		 * </code>
		 * 
		 * TODO: finish
		 * 
		 */
		if(false){
			var re = /d(b+)(d)/ig;
			var result = re.exec("cdbBdbsbz");
			
			if(result.index != 1){
				throw new Error("result.index !== 1, but: " + result.index);
			}
			if(result.input != "cdbBdbsbz"){
				throw new Error("result.input must contain original string, but: " + result.input);
			}
			if(result[0] !== 'dbBd'){
				throw new Error("result[0] !== 'dbBd', but: " + result[0]);
			}
		}
	}
	/**
	if(!(/'/g)){
		throw "Inline RegExp";
	}
	if(!(/"/g)){
		throw "Inline RegExp";
	}
	*/
}
out.println("done.");

out.print("ERROR check tests...");
{
	if(!Error){
		throw "no Error global object accessible!";
	}
	if(!RangeError){
		throw "no RangeError global object accessible!";
	}
	if(!TypeError){
		throw "no TypeError global object accessible!";
	}
	if(!Error.prototype){
		throw "no Error.prototype accessible!";
	}
	if(!RangeError.prototype){
		throw "no RangeError.prototype accessible!";
	}
	if(!TypeError.prototype){
		throw "no TypeError.prototype accessible!";
	}
	for each(var error in [
	                       { message : 'error' },
	                       new Error('error'),
	                       new EvalError('error'),
	                       new RangeError('error'),
	                       new ReferenceError('error'),
	                       new SyntaxError('error'),
	                       new TypeError('error'),
	                       new URIError('error'),
	                       ]){
		var r = false;
		try{
			throw error;
		}catch(e){
			r = e;
		}
		if(!r){
			throw new Error("error haven't been caught!");
		}
		if(r != error){
			throw new Error("not the same error have been caught!");
		}
		if(r.message != 'error'){
			throw new Error("Invalid message property, expected 'error', but: " + r.message);
		}
	}
}
out.println("done.");

out.print("for loop test...");
{
	{
		var sum = 0;
		for (var item = 5; item > 0; item --) {
			sum += item;
		}
		// 5 + 4 + 3 + 2 + 1 = 15
		if(sum != 15){
			throw "15 expected, but: " + sum;
		}
	}
	{
		var sum = 0;
		for (var item = 5; item; --item) {
			sum += item;
			if(item < 0){
				throw new Error("Wow! Negative!");
			}
		}
		// 5 + 4 + 3 + 2 + 1 = 15
		if(sum != 15){
			throw "15 expected, but: " + sum;
		}
	}
	{
		var testCounter = 100;
		for( var i = 50; i > 0; i-- ){
			for( var j = 1; j >=0; j-- ){
				testCounter --;
			}
		}
		if( testCounter ){
			throw;
		}
	}
	{
		// simple for( in ) test: object
		var sum = 0;
		var obj = {prop1: 5, prop2: 13, prop3: 8};
		for (var item in obj) {
			sum += item;
		}
		if(sum != '0prop1prop2prop3'){
			throw "'0prop1prop2prop3' expected, but: " + sum;
		}
	}
	{
		var debug = 'DEBUG: simple for keys( in ) test: array:';
		var sum = 0;
		for keys (var item in [ 15, 13, 8 ]) {
			sum += item;
			debug += item + ' ';
		}
		// 0 + 1 + 2 = 3
		if(sum != 3){
			throw "3 expected, but: " + sum + ", debug=" + debug;
		}
	}
	{
		var debug = 'DEBUG: simple for v677( in ) test: array: ';
		var sum = 0;
		for v677(var item in [ 15, 13, 8 ]) {
			sum += item;
			debug += item + ' ';
		}
		if(sum != 36){ // 15 + 13 + 8 = 36
			throw "36 expected, but: " + sum + ", debug=" + debug;
		}
	}
	{
		var debug = 'DEBUG: simple for v677 ( in ) test: array (have whitespace): ';
		var sum = 0;
		for v677 (var item in [ 15, 13, 8 ]) {
			sum += item;
			debug += item + ' ';
		}
		if(sum != 36){ // 15 + 13 + 8 = 36
			throw "36 expected, but: " + sum + ", debug=" + debug;
		}
	}
	{
		var debug = 'DEBUG: v677 blindly applied to for(;;) type loop: ';
		var sum = 0;
		for v677(var item = 0; item < 3; item++) {
			sum += item;
			debug += item + ' ';
		}
		if(sum != 3){ // 0 + 1 + 2 = 3
			throw "3 expected, but: " + sum + ", debug=" + debug;
		}
	}
	{
		var debug = 'DEBUG: v677 blindly applied to for (;;) type loop (have whitespace): ';
		var sum = 0;
		for v677 (var item = 0; item < 3; item++) {
			sum += item;
			debug += item + ' ';
		}
		if(sum != 3){ // 0 + 1 + 2 = 3
			throw "3 expected, but: " + sum + ", debug=" + debug;
		}
	}
	{
		var debug = 'DEBUG: simple for each( in ) test: array:';
		var sum = 0;
		for each (var item in [ 15, 13, 8 ]) {
			sum += item;
			debug += item + ' ';
		}
		if(sum != 36){ // 15 + 13 + 8 = 36
			throw "36 expected, but: " + sum + ", debug=" + debug;
		}
	}
	{
		// simple for each( in ) test: object
		// from MDC
		var sum = 0;
		var obj = {prop1: 5, prop2: 13, prop3: 8};
		for each (var item in obj) {
			sum += item;
		}
		// print(sum); // prints "26", which is 5+13+8
		if(sum != 26){
			throw "26 expected, but: " + sum;
		}
	}
	{
		// simple for each( in ) test: array
		var sum = 0;
		var obj = [ 15, 13, 8 ];
		for each (var item in obj) {
			sum += item;
		}
		if(sum != 36){
			throw "36 expected, but: " + sum;
		}
	}
	{
		var test = "";
		// $output known to be working already
		$output(test){
			= "start;";
			var a = "end;";
			var j = -100;
			for(j = 0; false; a = "error"){
				// empty
			}
			for(var i = 0; i < 12; i++, j++){
				// empty
			}
			for(var i = 0; i < 10; i++){
				= i;
				= ";";
			}
			= a;
			= "[j="+j+']';
		}
		if( test != "start;0;1;2;3;4;5;6;7;8;9;end;[j=12]"){
			throw "for/loop 1: test=" + test;
		}
	}
	{
		var path = ['aa','bb','cc','dd'];
		var pathLeft = path.length;
		if(typeof pathLeft != "number"){
			throw "expected to be number: " + pathLeft;
		}
		if(pathLeft != 4){
			throw "expected to be equal to 4, but: " + pathLeft;
		}
		var test = "";
		// $output known to be working already
		$output(test){
			for(var current in path){
				%>[[<%
					pathLeft --;
					for(var i = pathLeft; i > 0; i--){ 
						= "../"; 
					}
				%>]<% = current ? current : "root" %> ]<%
			}
		}
		if( test != "[[../../../]aa ][[../../]bb ][[../]cc ][[]dd ]"){
			throw "for/loop 2: test=" + test;
		}
	}
	{
		var path = ['aa','bb','cc','dd'];
		var pathLeft = path.length;
		if(typeof pathLeft != "number"){
			throw "expected to be number: " + pathLeft;
		}
		if(pathLeft != 4){
			throw "expected to be equal to 4, but: " + pathLeft;
		}
		var test = "";
		// $output known to be working already
		$output(test){
			for each(var current in path){
				%>[[<%
					pathLeft --;
					for(var i = pathLeft; i > 0; i--){ 
						= "../"; 
					}
				%>]<% = current ? current : "root" %> ]<%
			}
		}
		if( test != "[[../../../]aa ][[../../]bb ][[../]cc ][[]dd ]"){
			throw "for/loop 2: test=" + test;
		}
	}
}
out.println("done.");

out.print("for loop break test...");
{
	// simple
	{
		var truefor = 0;
		for(;;){
			if(truefor ++ > 100) break;
			if(truefor > 200) throw;
		}
		if(truefor != 102){
			throw "break test failed";
		}
	}
	// try/catch
	{
		var truefor = 0;
		for(;;){
			try{
				if(truefor ++ > 100) break;
				if(truefor > 200) throw;
			}catch(e){
				//
			}
		}
		if(truefor != 102){
			throw "break test failed";
		}
	}
}
out.println("done.");

out.print("for loop continue test...");
{
	// simple
	{
		var c = 0, d = 0;
		for(var i = 0; i < 10; i++){
			c++;
			continue;
			d++;
		}
		if(d != 0){
			throw "variable after 'continue' statement expected to be zero!";
		}
		if(c != 10){
			throw "loop counter expected to be 10";
		}
	}
	// try/catch 1
	{
		var c = 0, d = 0, e = 0;
		for(var i = 0; i < 10; i++){
			c++;
			try{
				continue;
			}catch(t){
			 e++;
			}
			d++;
		}
		if(d != 0){
			throw "variable after 'try/catch' statement expected to be zero!";
		}
		if(e != 0){
			throw "variable in 'catch' statement expected to be zero!";
		}
		if(c != 10){
			throw "loop counter expected to be 10";
		}
	}
	// try/catch 2
	{
		var c = 0, d = 0, e = 0;
		for(var i = 0; i < 10; i++){
			c++;
			try{
				throw;
			}catch(t){
				e++;
				continue;
			}
			d++;
		}
		if(d != 0){
			throw "variable after 'try/catch' statement expected to be zero!";
		}
		if(e != 10){
			throw "variable in 'catch' statement expected to be 10";
		}
		if(c != 10){
			throw "loop counter expected to be 10";
		}
	}
	// try/catch 3
	{
		var c = 0, d = 0, e = 0;
		try{
			for(var i = 0; i < 10; i++){
				c++;
				throw "Oops!";
			}
			d++;
		}catch(t){
			if(t != "Oops!"){
				throw "Exception should be equal to: 'Oops!', but: " + t;
			}
			e++;
		}
		if(d != 0){
			throw "variable after 'try/catch' statement expected to be zero!";
		}
		if(e != 1){
			throw "variable in 'catch' statement expected to be 1, but: " + e;
		}
		if(c != 1){
			throw "loop counter expected to be 10, but: " + c;
		}
	}
	// try/catch 4
	{
		var c = 0, d = 0, e = 0;
		function test(){
			throw "Oops!";
		}
		try{
			for(var i = 0; i < 10; i++){
				c++;
				test();
			}
			d++;
		}catch(t){
			if(t != "Oops!"){
				throw "Exception should be equal to: 'Oops!', but: " + t;
			}
			e++;
		}
		if(d != 0){
			throw "variable after 'try/catch' statement expected to be zero!";
		}
		if(e != 1){
			throw "variable in 'catch' statement expected to be 1, but: " + e;
		}
		if(c != 1){
			throw "loop counter expected to be 10, but: " + c;
		}
	}
}
out.println("done.");

out.print("array ACALL test...");
{
	function f(){
		return "OK";
	}
	var list = [];
	list.push(f);
	if(!list.length){
		throw "list length doesn't exist";
	}
	if(list.length != 1){
		throw "list length expected to be 1";
	}
	if(!list[0]){
		throw "first list element is undefined";
	}
	
	var element = list[0];
	if( element() != "OK" ){
		throw;
	}
	if( list[0]() != "OK"){
		throw;
	}
}
out.println("done.");

out.print("array ACALL test (2 - global functions)...");
{
	var list = [Array, Number];
	if( !list[0](null) ){
		throw;
	}
	if( list[1](null) ){
		throw;
	}
	if( list[0]( [1,2,3,4,5,6] ).length != 6 ){
		throw;
	}
	if( list[1]( Math.ceil(5.7) ) != 6 ){
		throw;
	}
}
out.println("done.");

out.print("ACALL tests extended (0)...");
{
	function f(zz){
		for( var i in [1,2,3,4,5] ){
			if( i == zz ){
				return "222"+i;
			}
		}
	}
	if( f(2) != "2222" ){
		throw "result should be equal to 2222 (0), but: " + f(2);
	}
}
out.println("done.");

out.print("ACALL tests extended (1)...");
{
	function f(zz){
		try{
			for( var i in [1,2,3,4,5] ){
				if( i == zz ){
					return "222"+i;
				}
			}
		}catch(e){
			for( var i in [1,2,3,4,5] ){
				if( i == 2 ){
					return "error";
				}
			}
		}
	}
	if( f(2) != "2222" ){
		throw;
	}
}
out.println("done.");

out.print("ACALL tests extended (2) ...");
{
	function f(zz){
		try{
			var a2;
			for( var i in [1,2,3,4,5] ){
				if( i == zz ){
					return "222"+i;
				}
			}
		}catch(e){
			for( var i in [1,2,3,4,5] ){
				if( i == 2 ){
					return "error";
				}
			}
		}
	}
	if( f(2) != "2222" ){
		throw;
	}
}
out.println("done.");

out.print("ACALL tests extended (3) ...");
{
	function f(zz){
		var a1;
		try{
			for( var i in [1,2,3,4,5] ){
				if( i == zz ){
					return "222"+i;
				}
			}
		}catch(e){
			for( var i in [1,2,3,4,5] ){
				if( i == 2 ){
					return "error";
				}
			}
		}
	}
	if( f(2) != "2222" ){
		throw;
	}
}
out.println("done.");

out.print("ACALL tests extended (4) ...");
{
	function f(zz){
		var a1;
		try{
			var a2;
			for( var i in [1,2,3,4,5] ){
				if( i == zz ){
					return "222"+i;
				}
			}
		}catch(e){
			for( var i in [1,2,3,4,5] ){
				if( i == 2 ){
					return "error";
				}
			}
		}
	}
	if( f(2) != "2222" ){
		throw;
	}
}
out.println("done.");

out.print("ACALL tests extended (5) ...");
{
	const type = { stream : "stream1" };
	const NDS = { NMP : { stream1 : function(s, l, b, t){
		return "" + s + "" + l + "-" + b + "!";
	} } };
	const start = "Start", limit = 5, backwards = true, parameters = {};
	const stats = NDS.NMP[type.stream](start, limit + 1, backwards, parameters.test);
	if(stats != "Start6-true!"){
		null.null();
	}
}
out.println("done.");

out.print("getters...");
{
	{
		var a = Object.create(Object.prototype, {
			"a" : {
				value : 11
			},
			"b" : {
				value : 12
			},
			"c" : {
				value : 13
			},
			"a0" : {
				get : function(){
					return this.a + this.b + this.c;
				}
			},
			"a1" : {
				value : function(a, b, c){
					return a + b + c;
				}
			},
			"a2" : {
				get : function(){
					return function(a, b, c){
						return a + b + c;
					};
				}
			},
			"b2" : {
				get : function(){
					return function(a, b){
						return a + b;
					};
				}
			},
			"c1" : {
				get : function(){
					return function(a){
						return a * 2;
					};
				}
			},
			"d0" : {
				get : function(){
					return function(){
						return this.a * 2;
					};
				}
			}
		});

		var r;

		if( (r = a.a0) !== 36 ){
			throw new Error("error (a) " + r + " !== 36");
		}
		if( (r = a.a1(11,12,14)) !== 37 ){
			throw new Error("error " + r + " !== 37");
		}
		if( (r = a.a2(11,12,15)) !== 38 ){
			throw new Error("error " + r + " !== 38");
		}
		
		{
			var b = {
				a : a
			};

			if( (r = b.a.a0) !== 36 ){
				throw new Error("error (b.a) " + r + " !== 36");
			}
			if( (r = b.a.a1(21,12,14)) !== 47 ){
				throw new Error("error " + r + " !== 47");
			}
			if( (r = b.a.a2(21,12,15)) !== 48 ){
				throw new Error("error " + r + " !== 48");
			}
			if( (r = b.a.b2(21,12)) !== 33 ){
				throw new Error("error " + r + " !== 33");
			}
			if( (r = b.a.c1(21)) !== 42 ){
				throw new Error("error " + r + " !== 42");
			}
			if( (r = b.a.d0()) !== 22 ){
				throw new Error("error " + r + " !== 22");
			}
		}
		
		{
			var c = Object.create(null, {
				x : {
					value : 7
				},
				y : {
					value : 13
				},
				xx : {
					get : function(){
						return this.x;
					}
				},
				yy : {
					get : function(){
						return this.y;
					}
				},
				s : {
					get : function(){
						return this.xx + this.yy;
					}
				},
			});
			
			if( (r = c.s) !== 20 ){
				throw new Error("error " + r + " !== 20");
			}
		}
		
	}
}
out.println("done.");

out.print("super store...");
{
	var o = {
		a	: 'a',
		b	: 'b',
		cc	: 'c',
		c	: function(x){
			return this[x];
		},
		n1	: 'nn1',
		nn1	: 1,
		dd	: 'd',
		d	: function(x){
			return this[this.cc](this[x.a]);
		},
		ddd	: function(k){
			return k || this.dd;
		},
		eee	: function(){
			return this;
		}
	};
	var oo = { o : o };
	var ooo = { oo : oo };
	
	ooo.oo.o.eee()[ooo.oo.o.eee().ddd('zz')+'z'] = ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o.zzz != 1){
		throw "Must be 1, but: " + o.zzz;
	}
	ooo.oo.o.eee()[ooo.oo.o.eee().ddd('zz')+'z'] += ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o.zzz != 2){
		throw "Must be 2, but: " + o.zzz;
	}
	ooo.oo.o.eee()[ooo.oo.o.eee().ddd('zz')+'z'] /= ooo.oo.o.eee()[ooo.oo.o.eee().n1] * 2;
	if(o.zzz != 1){
		throw "Must be 1, but: " + o.zzz;
	}
	ooo.oo.o.eee()[ooo.oo.o.eee().ddd('zz')+'z'] *= ooo.oo.o.eee()[ooo.oo.o.eee().n1] * 2;
	if(o.zzz != 2){
		throw "Must be 2, but: " + o.zzz;
	}
	ooo.oo.o.eee()[ooo.oo.o.eee().ddd('zz')+'z'] -= ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o.zzz != 1){
		throw "Must be 1, but: " + o.zzz;
	}
	o.index = 1;
	ooo.oo.o.eee()[ooo.oo.o.eee().ddd('zz')+ (o.index++) ] = ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o.zz1 != 1){
		throw "Must be 1, but: " + o.zz1;
	}
	ooo.oo.o.eee()[o.index++] = ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o['2'] != 1){
		throw "Must be 1, but: " + o['2'];
	}
	if(o[2] != 1){
		throw "Must be 1, but: " + o[2];
	}
	ooo.oo.o.eee()[o.index -= 1] = ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o['2'] != 1){
		throw "Must be 1, but: " + o['2'];
	}
	if(o[2] != 1){
		throw "Must be 1, but: " + o[2];
	}
	ooo.oo.o.eee()[o.index--] = ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o['2'] != 1){
		throw "Must be 1, but: " + o['2'];
	}
	if(o[2] != 1){
		throw "Must be 1, but: " + o[2];
	}
	ooo.oo.o.eee()[++o.index] = ooo.oo.o.eee()[ooo.oo.o.eee().n1];
	if(o['2'] != 1){
		throw "Must be 1, but: " + o['2'];
	}
	if(o[2] != 1){
		throw "Must be 1, but: " + o[2];
	}
}
out.println("done.");

out.print("super call...");
{
	var o = {
		a : 'a',
		b : 'b',
		cc: 'c',
		c : function(x){
			return this[x];
		},
		dd: 'd',
		d : function(x){
			return this[this.cc](this[x.a]);
		},
		ddd: function(){
			return this.dd;
		}
	};
	var r = o[o.cc](o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = o[o.cc](x = o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = o[o.cc](0, x = o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = o[o.cc](z = 0, x = o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = o[o.cc](o.a, o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}

	var oo = { o : o }
	var r = oo.o[oo.o.cc](oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.cc](x = oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.cc](0, x = oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.cc](z = 0, x = oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.cc](oo.o.a, oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.dd](oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.ddd()](oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.dd](x = oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.ddd()](x = oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.dd](0, x = oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.ddd()](0, x = oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.dd](z = 0, x = oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.ddd()](z = 0, x = oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.dd](oo.o, oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = oo.o[oo.o.ddd()](oo.o, oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	
	var ooo = { oo : oo };
	var r = ooo.oo.o[ooo.oo.o.cc](ooo.oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.cc](x = ooo.oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.cc](0, x = ooo.oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.cc](z = 0, x = ooo.oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.cc](ooo.oo.o.a, ooo.oo.o.a);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.dd](ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.ddd()](ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.dd](x = ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.ddd()](x = ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.dd](0, x = ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.ddd()](0, x = ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.dd](z = 0, x = ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.ddd()](z = 0, x = ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.dd](ooo.oo.o, ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.ddd()](ooo.oo.o, ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}

	o.index = 1;
	ooo.oo.o[o.index] = ooo.oo.o[ooo.oo.o.ddd()];
	if(typeof (o[1]) != 'function'){
		throw "Gotta be function, but: " + o[1];
	}
	var r = ooo.oo.o[ooo.oo.o.index++](ooo.oo.o, ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.index -= 1](ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[ooo.oo.o.index--](ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
	var r = ooo.oo.o[++ooo.oo.o.index](ooo.oo.o);
	if(r != 'a'){
		throw "Must be 'a', but: " + r;
	}
}
out.println("done.");

out.print("array loops...");
{
	{
		var test = [ 0, 1, 2, 3, 4 ];
		var iSum = 0, vSum = 0;
		for(var i in test){
			iSum += i;
			vSum += test[i];
		}
		if(iSum != vSum){
			throw "Should be equal: " + iSum + " != " + vSum;
		}
	}
	{
		var test = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ];
		var iSum = 0, vSum = 0;
		for(var i in test){
			iSum += i;
			vSum += test[i];
		}
		if(iSum != vSum){
			throw "Should be equal: " + iSum + " != " + vSum;
		}
	}
	{
		var test = [ 0, 1, 2, 3, 4 ];
		var iSum = '', vSum = '';
		for(var i in test){
			iSum += i;
			vSum += test[i];
		}
		if(iSum != vSum){
			throw "Should be equal: " + iSum + " != " + vSum;
		}
	}
	{
		var test = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 0 ];
		var iSum = '', vSum = '';
		for(var i in test){
			iSum += i;
			vSum += test[i];
		}
		if(iSum != vSum){
			throw "Should be equal: " + iSum + " != " + vSum;
		}
	}
}
out.println("done.");

out.print("empty loops...");
{
	{
		var value = 0;
		for(var j in false){
			value ++;
		}
		if(value){
			throw "empty loop is not empty: false";
		}
	}
	var tests = [undefined, null, false, 0, ""];
	var testCount = 0;
	for(var i in tests){
		var value = 0;
		for(var j in i){ // ECMA-262: 'tests[i]' instead of 'i'
			value ++;
		}
		if(value){
			throw "empty loop is not empty: " + tests[i];
		}
		testCount ++;
	}
	if(testCount != tests.length){
		throw "invalid testCount=" + testCount + ", tests.length=" + tests.length;
	}
}
out.println("done.");

out.print("String.prototype.split...");
{
	
	if( !Array.isArray("split".split("")) ){
		throw "split: split by empty string: split result in not an array: " + "split".split("");
	}
	if( 5 !== "split".split("").length ){
		throw "split: split by empty string: wrong array length: " + "split".split("");
	}
	if( "tilps" !== "split".split("").reverse().join("") ){
		throw "split: split by empty string - reverse - join: is not reversed: " + "split".split("").reverse().join("");
	}
	
	function checkSplit(s, n){
		if( s.split(';').length !== n ){
			throw "split, expected " + n + " parsing " + Format.jsObject(s) + ", array: " + Format.jsObject(s.split(';'));
		}
	}
	checkSplit("", 1);
	checkSplit("a", 1);
	checkSplit("aa", 1);
	checkSplit(";", 2);
	checkSplit("a;", 2);
	checkSplit("a;a", 2);
	checkSplit("aa;aa", 2);
	checkSplit(";;", 3);
	checkSplit("a;;", 3);
	checkSplit("a;a;", 3);
	checkSplit("a;;a", 3);
	checkSplit(";a;a", 3);
	checkSplit(";;a", 3);
	checkSplit("a;a;a", 3);
	checkSplit("1;4;;", 4);
}
out.println("done.");

out.print("indexOf/substring...");
{
	var param = "10/6";
	var type = -5, size = -7;
	if( param.indexOf("/") != 2){
		throw "indexOf should be 2, but: " + param.indexOf("/");
	}
	if( param.indexOf("/") != -1 ){
		var check;
		check = param.substring(param.indexOf("/") + 1);
		if( check != "6" ){
			throw "substring is broken, check should be '6', but: " + check;
		}
		type = Math.round(Number( param.substring(param.indexOf("/") + 1), type ));
		check = param.substring(0, param.indexOf("/"));
		if( check != "10" ){
			throw "substring is broken, check should be '10', but: " + check;
		}
		size = Math.round(Number( param.substring(0, param.indexOf("/")), size ));
	}
	if( type != 6 ){
		throw "type should be 6, but: " + type;
	}
	if( size != 10 ){
		throw "size should be 10, but: " + size;
	}
}
out.println("done.");

out.print("entrySet/keySet/values...");
{
	var map = {
		a : 5,
		b : 6,
		c : 7
	};
	{
		var check = "", count = 0;
		for(var i in map){
			check += i;
			count ++;
		}
		if(check != "abc"){
			throw "check is not 'abc', but: " + check + ", keySet=" + map.keySet() + ", count=" + count;
		}
	}
	{
		var check = "", count = 0;
		for(var i in map.keySet()){
			check += i;
			count ++;
		}
		if(check != "abc"){
			throw "check is not 'abc', but: " + check + ", keySet=" + map.keySet() + ", count=" + count;
		}
	}
	{
		var check = "", count = 0;
		for(var i in map.entrySet()){
			if(i.getKey() != i.key){
				throw "Reflection is broken: getKey() != key: getKey()=" + i.getKey() + ", key=" + i.key + ", i=" + i;
			}
			check += i.key;
			count ++;
		}
		if(check != "abc"){
			throw "check is not 'abc', but: " + check + ", count=" + count;
		}
	}
	{
		var check = "", count = 0;
		for(var i in map.entrySet()){
			if(i.getValue() != i.value){
				throw "Reflection is broken: getValue() != value: getValue()=" + i.getValue() + ", value=" + i.value + ", i=" + i;
			}
			check += i.value;
			count ++;
		}
		if(check != "567"){
			throw "check is not '567', but: " + check + ", count=" + count;
		}
	}
	{
		var check = "", count = 0;
		for(var i in map.values()){
			check += i;
			count ++;
		}
		if(check != "567"){
			throw "check is not '567', but: " + check + ", count=" + count;
		}
	}
}
out.println("done.");

out.print("It is a pity, but access to undefined's property was a really nice feature...");
{
	var az;
	try{
		var x = az.aa;
	}catch(e){
		az = true;
	}
	if(!az){
		throw "access of undefined's property should throw an exception.";
	}
	az = null;
	try{
		var x = az.aa;
	}catch(e){
		az = true;
	}
	if(!az){
		throw "access of null's property should throw an exception.";
	}
	/**
	 * following should work regardless
	 */
	az = 5; az = az.aa;
	if(az !== undefined){
		throw "Oops, az=" + az + ", typeof=" + (typeof az);
	}
	/**
	 * following should work regardless
	 */
	az = false; az = az.aa;
	if(az !== undefined){
		throw "Oops, az=" + az + ", typeof=" + (typeof az);
	}
}
out.println("done.");

out.print("reflection getXXX preference over property...");
{
	var check = { a : 'success' };
	var FieldsLookup = { "b" : { a : 'success' }, 'bsuccess' : check, "class" : check, classsuccess : check };
	var Record = { b : 'a', "class" : 'a' };
	for(var Current in ['b','class']){
		var value = FieldsLookup[Current].get(Record[Current]);
		if(value != 'success'){
			throw "key is '" + Current + "', value is not success: " + value;
		}
		var value = FieldsLookup[Current + FieldsLookup[Current].get(Record[Current])].get(Record[Current]);
		if(value != 'success'){
			throw "key is '" + Current + "', value is not success: " + value;
		}
	}
}
out.println("done.");

out.print("Nested class access...");
{
	import ru.myx.ae3.test.ecma.classes.NestedClassContainer;
	out.print(".");
	if(!NestedClassContainer){
		throw "Test case is not accessible!";
	}
	out.print(".");
	if(!NestedClassContainer.NestedInstanceClass){
		throw "Nested instance class is not accessible!";
	}
	out.print(".");
	if(!NestedClassContainer.NestedInstanceClass){
		throw "Nested static class is not accessible!";
	}
	out.print(".");
	if(NestedClassContainer.NestedInstanceClass_NON_EXISTENT){
		throw "Nested class non-existent constant is not null!";
	}
}
out.println("done.");

out.print("Enum values access...");
{
	const EnumTest = require('java.class/ru.myx.ae3.test.ecma.classes.EnumTest');
	out.print(".");
	if(!EnumTest){
		throw "Test case is not accessible!";
	}
	out.print(".");
	if(!EnumTest.VALUE1){
		throw new Error("Cannot access existing enum constant!");
	}
	out.print(".");
	if(EnumTest.VALUE_NON_EXISTENT){
		throw "Non-existing enum constant returns non-null value!";
	}
}
{
	import ru.myx.ae3.test.ecma.classes.EnumTest;
	out.print(".");
	if(!EnumTest){
		throw "Test case is not accessible!";
	}
	out.print(".");
	if(!EnumTest.VALUE1){
		throw new Error("Cannot access existing enum constant!");
	}
	out.print(".");
	if(EnumTest.VALUE_NON_EXISTENT){
		throw "Non-existing enum constant returns non-null value!";
	}
}
out.println("done.");

out.print("Semicolon insertion...");
{
	/**
	 * are we sane?
	 */
	{
		function x(){
		}
		
		x.prototype.a = function(x){
		};
		
		function y(){
		}
		
		if(!y){
			throw "Explicit semicolon is not here!?";
		}
	}
	/**
	 * check actual semicolon
	 */
	{
		function x(){
		}
		
		x.prototype.a = function(x){
		}
		
		function y(){
		}
		
		if(!y){
			throw "Semicolon was not inserted after assignment!";
		}
	}
	/**
	 * 
	 */
	{
		function t(){
		}
		
		function x(){
		}
		
		x.prototype.b = function b(x){
		};
		x.prototype.a = function a(x){
		}
		x.prototype.c = function c(x){
		}
		
		function y(){
		}
		
		function z(){
		}
		
		if(!y){
			throw "Semicolon was not inserted after assignment ('y')!";
		}
		if(!x.prototype.a){
			throw "Semicolon was not inserted after assignment ('a')!";
		}
		if(!x.prototype.c){
			throw "Semicolon was not inserted after assignment ('c')!";
		}
	}
}
out.println("done.");

out.print("reserved words...");
{
	for each(var test in [
	                      //"{ class : 'aaa' }", 
	                      "{ typeof : 'aaa' }", 
	                      // "{ new : 'aaa' }", 
	                      "{ delete : 'aaa' }", 
	                      // "( class = a )",
	                      "( typeof = a )",
	                      // "( new = a )",
	                      "( delete = a )",
	                      "( this = a )",
	                      ]){
		var result;
		try{
			result = eval( '(' + test + ')' );
		}catch(t){
			continue;
		}
		throw "Should fail before! test is: '" + test + "', result is: " + result;
	}
}
out.println("done.");

out.print("other syntax errors...");
{
	var result = [
	  "5 = 2",
	  "5 += 2",
	  "5 -= 2",
	  "5 /= 2",
	  "5 *= 2",
	  "5 %= 2",
	  
	  "Number(5) = 2",
	  "Number(5) += 2",
	  "Number(5) -= 2",
	  "Number(5) /= 2",
	  "Number(5) *= 2",
	  "Number(5) %= 2",
	  
	  " += 2",
	  " -= 2",
	  " /= 2",
	  " *= 2",
	  " %= 2",
	  
	  " += a",
	  " -= a",
	  " /= a",
	  " *= a",
	  " %= a",
	  
	  "{ a b c d }",
	  "{ a : , b : }",
	  "{ a : 5, b : }",
	  "{ a : , b : 6 }",
	  "{ a = 5, b = 6 }", 
	  "{ a = 'aaa', b = 'bbb' }", 
	  "{ a : 5 + 2), b : 'bbb' }", 
	  "{ a : 5 + 2], b : 'bbb' }", 
	  "{ a : (5 + 2}), b : 'bbb' }", 
	  
	  "a['b]", 
	  "a[\"b]", 
	  "a['b\\']", 
	  "a[\"b\\\"]", 
	  "a['b\\u']", 
	  "a[\"b\\u\"]", 
	  
	  "a[b)]", 
	  "a[b.)]", 
	  "a[b.c)]", 
	  "a[b}]", 
	  "a[b.}]", 
	  "a[b.c}]", 
	  "a[b)]", 
	  "a[b.)]", 
	  "a[b.c)]", 
	  
	  "a:b", 
	  "a.:b", 
	  "a+:b", 
	  "a.c:b",
	  
	  "a + 1xFF", 
	  "a. + 1xFF",
	  
	  "?a:b",
	  " ? a : b ",
	  "a ? b || c", 
	  "a! = b || c",
	  
	  "a = 2 * (b + c", 
	  "a = 2 * (b + c}", 
	  "a = 2 * (b + c]", 
	  "a = 2 * (b + c))", 
	  "a = 2 * (b + 3", 
	  "a = 2 * (b + 3}", 
	  "a = 2 * (b + 3]", 
	  "a = 2 * (b + 3))",
	  
	  "a = [1,2,3][1", 
	  "a = [1,2,3][1}", 
	  "a = [1,2,3][1)", 
	  "a = [1,2,3][1]]",
	  "a = [1,2,3][1.5", 
	  "a = [1,2,3][1.5}", 
	  "a = [1,2,3][1.5)", 
	  "a = [1,2,3][1.5]]",
	  
	  "a = [1,2,3][b", 
	  "a = [1,2,3][b}", 
	  "a = [1,2,3][b)", 
	  "a = [1,2,3][b]]",
	  "a = [1,2,3][b+", 
	  "a = [1,2,3][b+}", 
	  "a = [1,2,3][b+)", 
	  "a = [1,2,3][b]]",
	  
	  // "new (Error)",
	  // "new ()",
	  "new )",
	  "new }",
	  "new  }",
	  "new ]",
	  "new ,",
	  "new : old",
	  
	  "new Object blah",
	  "new Object( 'asasassa )",
	  "new Object( \"asasassa )",
	  "new Object(",
	  "new Object(}",
	  "new Object(]",
	  "new Object())",
	  "new Object(4",
	  "new Object(4}",
	  "new Object(4]",
	  "new Object(4))",
	  "new Object('\\t4'",
	  "new Object('\\t4'}",
	  "new Object('\\t4']",
	  "new Object('\\t4'))",
	  "new Object(\"\\t4\"",
	  "new Object(\"\\t4\"}",
	  "new Object(\"\\t4\"]",
	  "new Object(\"\\t4\"))",
	  
	  // "Object blah",
	  "Object( 'asasassa )",
	  "Object( \"asasassa )",
	  "Object(",
	  "Object(}",
	  "Object(]",
	  "Object())",
	  "Object(4",
	  "Object(4}",
	  "Object(4]",
	  "Object(4))",
	  'Object(\'\\t4\'',
	  "Object('\\t4'}",
	  "Object('\\t4']",
	  "Object('\\t4'))",
	  "Object(\"\\t4\"",
	  'Object("\\t4"}',
	  'Object("\\t4"]',
	  'Object("\\t4"))',
	  
	  "Array.prototype.toString( 'asasassa )",
	  "Array.prototype.toString( \"asasassa )",
	  "Array.prototype.toString(",
	  "Array.prototype.toString(}",
	  "Array.prototype.toString(]",
	  "Array.prototype.toString())",
	  "Array.prototype.toString(4",
	  "Array.prototype.toString(4}",
	  "Array.prototype.toString(4]",
	  "Array.prototype.toString(4))",
	  "Array.prototype.toString('\\t4'",
	  "Array.prototype.toString('\\t4'}",
	  "Array.prototype.toString('\\t4']",
	  "Array.prototype.toString('\\t4'))",
	  'Array.prototype.toString("\\t4"',
	  'Array.prototype.toString("\\t4"}',
	  'Array.prototype.toString("\\t4"]',
	  'Array.prototype.toString("\\t4"))',
	  
	  "function f(a, b, ",
	  "function f(){ return '\\n\\r\\t + b; }, f()", 
	  "{ f : function(){ return '\\n\\r\\t + b; } }.f()", 
	  'function f(){ return "\\n\\r\\t + b; }, f()', 
	  '{ f : function(){ return "\\n\\r\\t + b; } }.f()',
	
	  /**
	   * TODO (below this)
	   */
	  undefined,
	  
	  "function (){ return a + b;", 
	  "function (){ aaa); return b; }, f()", 
	  "function (){ aaa]; return b; }, f()", 
	  "function (){ aaa.aa); return b; }, f()", 
	  "function (){ aaa.aa]; return b; }, f()", 
	  "function (){ var content.body = 5;", 
	  "function (){ var content.body = 5; }, f()", 
	
	  "function f(){ return a + b;", 
	  "function f(){ aaa); return b; }, f()", 
	  "function f(){ aaa]; return b; }, f()", 
	  "function f(){ aaa.aa); return b; }, f()", 
	  "function f(){ aaa.aa]; return b; }, f()", 
	  "function f(){ var content.body = 5;", 
	  "function f(){ var content.body = 5; }, f()", 
	  "{ f : function(){ var content.body = 5; } }.f()", 
	  "{ f : function(){ var content = 5; } }.f(", 
	].every(function(test){
		if(!test) {
			return false;
		}
		var result;
		try{
			result = eval( '(' + test + ')' );
		}catch(t){
			return true;
		}
		throw "Should fail before! test is: '" + test + "', result is: " + result;
	});
	if(!result){
		out.print("(warning: some skipped elements) ");
	}
}
out.println("done.");


out.print("direct/wrap optimisations...");
{
	{
		var s = 'sdasdsdasdas';
		var a = [];
		a.push(s + '123');
		a.push(s + '234');
		a[2] = s + '345';
		
		if(a[0] == a[1]){
			throw "Should differ: a[0]='" + a[0] + "', a[1]='" + a[1] + "'";
		}
		if(a[0] == a[2]){
			throw "Should differ: a[0]='" + a[0] + "', a[2]='" + a[2] + "'";
		}
		if(a[1] == a[2]){
			throw "Should differ: a[1]='" + a[1] + "', a[2]='" + a[2] + "'";
		}
	}
	{
		var map = {
			a : '1234567',
			b : '2345678',
			c : '3456789'
		};
		var a = [];
		
		for each(var entry in map.entrySet()){
			a.push(entry.value);
		}
		
		if(a[0] == a[1]){
			throw "Map, Should differ: a[0]='" + a[0] + "', a[1]='" + a[1] + "'";
		}
		if(a[0] == a[2]){
			throw "Map, Should differ: a[0]='" + a[0] + "', a[2]='" + a[2] + "'";
		}
		if(a[1] == a[2]){
			throw "Map, Should differ: a[1]='" + a[1] + "', a[2]='" + a[2] + "'";
		}
	}
}
out.println("done.");



out.print("extra comma...");
{
	{
		const s = [1,2,5,].toString();
		if(s != "1,2,5"){
			throw "Incorrect array in extra comma in array literal check: " + s;
		}
	}
	{
		const s = [].concat(1,2,3,).toString();
		if(s != "1,2,3"){
			throw "Incorrect array in extra comma in call arguments check: " + s;
		}
	}
	{
		const s = [7].concat(2,3,).toString();
		if(s != "7,2,3"){
			throw "Incorrect array in extra comma in call arguments check: " + s;
		}
	}
	{
		const s = [7,2,].concat(3,).toString();
		if(s != "7,2,3"){
			throw "Incorrect array in extra comma in call arguments check: " + s;
		}
	}
}
out.println("done.");



out.print("Nullish coalescing operator (??)...");
{
	{
		var map = {}, zapf = false, zapn = null, rz, fn;
		
		rz = (map.key ?? "hello!");
		if( rz !== "hello!" ) {
			throw "Incorrect result in nco1: " + rz;
		}
		
		rz = (zapf ?? "hello!");
		if( rz !== false ) {
			throw "Incorrect result in nco1f: " + rz;
		}
		
		rz = (zapn ?? "hello!");
		if( rz !== "hello!" ) {
			throw "Incorrect result in nco1n: " + rz;
		}
		
		rz = (function(){ return map.key ?? "hello!"; })();
		if( rz !== "hello!" ) {
			null();
			throw "Incorrect return #1 length in nco1: " + rz;
		}
		
		rz = (function(){ return zapf ?? "hello!"; })();
		if( rz !== false ) {
			null();
			throw "Incorrect return #1 length in nco1f: " + rz;
		}
		
		rz = (function(){ return zapn ?? "hello!"; })();
		if( rz !== "hello!" ) {
			null();
			throw "Incorrect return #1 length in nco1n: " + rz;
		}
		
		rz = (function(){ return (map.key ?? "hello!"); })();
		if( rz !== "hello!" ) {
			throw "Incorrect return #2 length in nco1: " + rz;
		}

		rz = (function(){return undefined;})() ?? "Hi there!";
		if( rz !== "Hi there!" ) {
			throw "Incorrect return #3 length in nco1: " + rz;
		}
	
		rz = (function(){return "Good Lord!";})() ?? "Hi there!";
		if( rz !== "Good Lord!" ) {
			throw "Incorrect return #4 length in nco1: " + rz;
		}

		fn = function(){return undefined;};
		rz = fn() ?? "Hi there!";
		if( rz !== "Hi there!" ) {
			throw "Incorrect return #5 length in nco1: " + rz;
		}
		map.fn = fn;
		rz = map.fn(map.fn) ?? "Hi there!";
		if( rz !== "Hi there!" ) {
			throw "Incorrect return #55 length in nco1: " + rz;
		}

		fn = function(){return "Good Lord!";};
		rz = fn() ?? "Hi there!";
		if( rz !== "Good Lord!" ) {
			throw "Incorrect return #6 length in nco1: " + rz;
		}
		map.fn = fn;
		rz = map.fn(map.fn) ?? "Hi there!";
		if( rz !== "Good Lord!" ) {
			throw "Incorrect return #66 length in nco1: " + rz;
		}
	}
	{
		var map = { key : false }, zapf = false, zapn = null, rz;
		
		rz = (map.key ?? "hello!");
		if( rz !== false ) {
			throw "Incorrect result in nco2: " + rz;
		}
		
		rz = (zapf ?? "hello!");
		if( rz !== false ) {
			throw "Incorrect result in nco2f: " + rz;
		}
		
		rz = (zapn ?? "hello!");
		if( rz !== "hello!" ) {
			throw "Incorrect result in nco2n: " + rz;
		}
		
		rz = (function(){ return map.key ?? "hello!"; })();
		if( rz !== false ) {
			throw "Incorrect return #1 length in nco2: " + rz;
		}
		
		rz = (function(){ return zapf ?? "hello!"; })();
		if( rz !== false ) {
			throw "Incorrect return #1 length in nco2f: " + rz;
		}
		
		rz = (function(){ return zapn ?? "hello!"; })();
		if( rz !== "hello!" ) {
			throw "Incorrect return #1 length in nco2n: " + rz;
		}
		
		rz = (function(){ return (map.key ?? "hello!"); })();
		if( rz !== false ) {
			throw "Incorrect return #2 length in nco2: " + rz;
		}
	}
	{
		var map = { def : "hello!" }, rz;
		
		rz = (map.key ?? map.def);
		if( rz !== "hello!" ) {
			throw "Incorrect result in nco3: " + rz;
		}
		
		rz = (function(){ return map.key ?? map.def; })();
		if( rz !== "hello!" ) {
			throw "Incorrect return #1 length in nco3: " + rz;
		}
		
		rz = (function(){ return (map.key ?? map.def); })();
		if( rz !== "hello!" ) {
			throw "Incorrect return #2 length in nco3: " + rz;
		}
	}
	{
		var map = { key : false, def : "hello!" }, rz;
		
		rz = (map.key ?? map.def);
		if( rz !== false ) {
			throw "Incorrect result in nco4: " + rz;
		}
		
		rz = (function(){ return map.key ?? map.def; })();
		if( rz !== false ) {
			throw "Incorrect return #1 length in nco4: " + rz;
		}
		
		rz = (function(){ return (map.key ?? map.def); })();
		if( rz !== false ) {
			throw "Incorrect return #2 length in nco4: " + rz;
		}
	}
}
out.println("done.");




out.print("Logical OR assignment (||=)...");
{
	{
		var map = { key : true }, rz;

		rz = (map.key ||= "hello").length;
		if( rz !== undefined ) {
			throw "Incorrect length in loa0: " + map.key;
		}
		if( map.key !== true ) {
			throw "Incorrect value in loa0: " + map.key;
		}
		
		rz = (function(){ return map.key ||= "hello"; })();
		if( rz !== true ) {
			null();
			throw new Error("Incorrect return #1 length in loa0: " + rz);
		}
		
		rz = (function(){ return map.key ||= "hello"; })().length;
		if( rz !== undefined ) {
			throw "Incorrect return #2 length in loa0: " + rz;
		}
		
		rz = (function(){ return (map.key ||= "hello"); })().length;
		if( rz !== undefined ) {
			throw "Incorrect return #3 length in loa0: " + rz;
		}
	}
	{
		var map = {}, zapn = null, zapf = false, rz;
		
		rz = (map.key ||= "hello").length;
		if( rz !== 5 ) {
			throw "Incorrect length in loa1: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in loa1: " + map.key;
		}
		
		rz = (function(){ return map.key2 ||= "hello2"; })();
		if( rz !== "hello2" ) {
			// null();
			throw "Incorrect returned value in loa10: " + rz;
		}
		
		rz = (function(){ return zapn ||= "hello3"; })();
		if( rz !== "hello3" ) {
			null();
			throw "Incorrect returned value in loa11: " + rz;
		}
		
		rz = (function(){ return zapf ||= "hello4"; })();
		if( rz !== "hello4" ) {
			// null();
			throw "Incorrect returned value in loa12: " + rz;
		}
		
		rz = (function(){ return (map.key2 ??= "hello2"); })().length;
		if( rz !== 6 ) {
			throw "Incorrect returned length in loa1: " + rz;
		}
		if( map.key2 !== "hello2" ) {
			throw "Incorrect value in loa11: " + map.key;
		}
	}
	{
		const map = { key : false };
		const rz = (map.key ||= "hello").length;
		if( rz !== 5 ) {
			throw "Incorrect length in loa2: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in loa2: " + map.key;
		}
	}
	{
		const map = { key : "" };
		const rz = (map.key ||= "hello").length;
		if( rz !== 5 ) {
			throw "Incorrect length in loa3: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in loa3: " + map.key;
		}
	}
	{
		const map = { key : 0 };
		const rz = (map.key ||= "hello").length;
		if( rz !== 5 ) {
			throw "Incorrect length in loa4: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in loa4: " + map.key;
		}
	}
	{
		const map = {};
		const helloFn = function(){ return "hello"; };
		const rz = (map.key ||= helloFn()).length;
		if( rz !== 5 ) {
			throw "Incorrect length in loa5: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in loa5: " + map.key;
		}
	}
	{
		const map = { obj : { text : "hello", hello : "hello!" } };
		const t1 = (map.obj ||= { text : "hello!" });
		const rz = (map.obj.text ||= map.obj.hello ).length;
		if( rz !== 5 ) {
			throw "Incorrect length in loa6: " + Format.jsObject(map);
		}
		if( map.obj.text !== "hello" ) {
			throw "Incorrect value in loa6: " + Format.jsObject(map);
		}
	}
	{
		const map = { obj : { text : "hello" } };
		const t1 = (map.ob1 ||= { text : "hello" });
		const rz = (map.ob1.tex1 ||= "hello!").length;
		if( rz !== 6 ) {
			throw "Incorrect length in loa7: " + Format.jsObject(map);
		}
		if( map.ob1.tex1 !== "hello!" ) {
			throw "Incorrect value in loa7: " + Format.jsObject(map);
		}
	}
	{
		const map = { obj : { text : "hello" } };
		const rz = ((map["obj".toLowerCase()] ||= {}).tex1 = "hello!");
		if( rz.length !== 6 ) {
			throw "Incorrect length in loa8: " + Format.jsObject(map);
		}
		if( map.obj.tex1 !== "hello!" ) {
			throw "Incorrect value in loa8: " + Format.jsObject(map);
		}
	}
}
out.println("done.");


out.print("Logical nullish assignment (??=)...");
{
	{
		var map = { key : false, key2 : false, key3 : false }, rz;
		
		rz = (map.key ??= "hello").length;
		if( rz !== undefined ) {
			throw "Incorrect length in lna0: " + map.key;
		}
		if( map.key !== false ) {
			throw "Incorrect value in lna0: " + map.key;
		}
		
		rz = (function(){ return map.key2 ??= "hello"; })().length;
		if( rz !== undefined ) {
			throw "Incorrect return length in lna0: " + map.key;
		}
		
		rz = (function(){ return (map.key3 ??= "hello"); })().length;
		if( rz !== undefined ) {
			throw "Incorrect return length in lna0: " + map.key;
		}
	}
	{
		var map = { key2 : null }, zapn = null, zapu = undefined, rz;
		
		rz = (map.key ??= "hello").length;
		if( rz !== 5 ) {
			throw "Incorrect length in lna1: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in lna1: " + map.key;
		}
		
		rz = (function(){ return map.key2 ??= "hello2"; })();
		if( rz !== "hello2" ) {
			// null();
			throw "Incorrect returned value in lna10: " + rz;
		}
		
		rz = (function(){ return zapn ??= "hello3"; })();
		if( rz !== "hello3" ) {
			// null();
			throw "Incorrect returned value in lna11: " + rz;
		}
		
		rz = (function(){ return zapu ??= "hello4"; })();
		if( rz !== "hello4" ) {
			// null();
			throw "Incorrect returned value in lna12: " + rz;
		}
		
		rz = (function(){ return (map.key2 ??= "hello2"); })().length;
		if( rz !== 6 ) {
			throw "Incorrect returned length in lna1: " + rz;
		}
		if( map.key2 !== "hello2" ) {
			throw "Incorrect value in lna11: " + map.key;
		}
	}
	{
		const map = {};
		const helloFn = function(){ return "hello"; };
		const rz = (map.key ??= helloFn()).length;
		if( rz !== 5 ) {
			throw "Incorrect length in lna5: " + map.key;
		}
		if( map.key !== "hello" ) {
			throw "Incorrect value in lna5: " + map.key;
		}
	}
	{
		const map = { obj : { text : "hello" } };
		const t1 = (map.obj ??= { text : "hello" });
		const rz = (map.obj.text ??= "hello!").length;
		if( rz !== 5 ) {
			throw "Incorrect length in lna6: " + Format.jsObject(map);
		}
		if( map.obj.text !== "hello" ) {
			throw "Incorrect value in lna6: " + Format.jsObject(map);
		}
	}
	{
		const map = { obj : { text : "hello" } };
		const t1 = (map.ob1 ??= { text : "hello" });
		const rz = (map.ob1.tex1 ??= "hello!").length;
		if( rz !== 6 ) {
			throw "Incorrect length in lna7: " + Format.jsObject(map);
		}
		if( map.ob1.tex1 !== "hello!" ) {
			throw "Incorrect value in lna7: " + Format.jsObject(map);
		}
	}
}
out.println("done.");



out.print("Optional chaining operator (?.): simple access...");
{
	let foo = { someFooProp1: "hi", tuc1 : { tuc1 : "toUpperCase" } };

	{
		{
			const bar = undefined;
			const rz = (bar?.someFooProp1); // undefined
			if( rz !== undefuned ) {
				throw "Incorrect result in oco#010: " + rz;
			}
		}
		{
			const rz01101 = (foo?.someFooProp1); // "hi"
			if( rz01101 !== "hi" ) {
				null.error(9001101);
				throw "Incorrect result in oco#011#1: " + rz01101;
			}
		}
		{
			const rz = (foo?.someFooProp1 + "!!"); // "hi!!"
			if( rz !== "hi!!" ) {
				throw "Incorrect result in oco#011#2: " + rz;
			}
		}
		{
			if( bar?.someFooProp1 + "!!" !== "undefined!!" ) {
				throw "Incorrect result in oco#011#3: " + (bar?.someFooProp1 + "!!");
			}
		}
		{
			if( foo?.someFooProp1 + "!!" !== "hi!!" ) {
				throw "Incorrect result in oco#011#4: " + (foo?.someFooProp1 + "!!");
			}
		}
		{
			const rz = (function(){ return foo?.someFooProp1; })(); // "hi"
			if( rz !== "hi" ) {
				null();
				throw "Incorrect result in oco#012#1: " + rz;
			}
		}
		{
			const rz = (function(){ return bar?.someFooProp1; })(); // "hi"
			if( rz !== undefined ) {
				null();
				throw "Incorrect result in oco#012#2: " + rz;
			}
		}
		{
			const rz = (function(){ return foo?.someFooProp1 + "!!"; })(); // "hi!!"
			if( rz !== "hi!!" ) {
				null();
				throw "Incorrect result in oco#012#3: " + rz;
			}
		}
		{
			const rz = (function(){ return bar?.someFooProp1 + "!!"; })(); // "undefined!!"
			if( rz !== "undefined!!" ) {
				null();
				throw "Incorrect result in oco#012#4: " + rz;
			}
		}
		{
			var rz = "NOT YET RUN";
			try{
				throw foo?.someFooProp1;
			}catch(e){
				rz = String(e);
			} // "hi"
			if( rz !== "hi" ) { 
				throw "Incorrect result in oco#013#1: " + rz;
			}
		}
		{
			var rz = "NOT YET RUN";
			try{
				throw foo?.someFooProp1 + "!!";
			}catch(e){
				rz = String(e);
			} // "hi!!"
			if( rz !== "hi!!" ) { 
				throw "Incorrect result in oco#013#2: " + rz;
			}
		}
	}
	{
		{
			const rz = (foo?.someFooProp1 ?? "not available"); // "hi"
			if( rz !== "hi" ) {
				throw "Incorrect result in oco#110: " + rz;
			}
		}
		{
			const rz = (foo?.someBarProp1 ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#111: " + rz;
			}
		}
	}
}
out.println("done.");


out.print("Optional chaining operator (?.): index access...");
{
	let foo = { someFooProp2: "hi", tuc2 : { tuc2 : "toUpperCase" } };

	{
		{
			const bar = undefined;
			const rz = (bar?.["someFooProp2"]); // undefined
			if( rz !== undefuned ) {
				throw "Incorrect result in oco#020: " + rz;
			}
		}
		{
			const rz = (foo?.["someFooProp2"]); // "hi"
			if( rz !== "hi" ) {
				throw "Incorrect result in oco#021#1: " + rz;
			}
		}
		{
			const rz = (foo?.["someFooProp2"] + "!!"); // "hi!!"
			if( rz !== "hi!!" ) {
				throw "Incorrect result in oco#021#2: " + rz;
			}
		}
		{
			if( bar?.["someFooProp2"] + "!!" !== "undefined!!" ) {
				throw "Incorrect result in oco#021#3: " + (bar?.someFooProp2 + "!!");
			}
		}
		{
			if( foo?.["someFooProp2"] + "!!" !== "hi!!" ) {
				throw "Incorrect result in oco#021#4: " + (foo?.someFooProp2 + "!!");
			}
		}
		{
			const rz = (function(){ return foo?.["someFooProp2"]; })(); // "hi"
			if( rz !== "hi" ) {
				null();
				throw "Incorrect result in oco#022#1: " + rz;
			}
		}
		{
			const rz = (function(){ return bar?.["someFooProp2"]; })(); // "hi"
			if( rz !== undefined ) {
				null();
				throw "Incorrect result in oco#022#2: " + rz;
			}
		}
		{
			const rz = (function(){ return foo?.["someFooProp2"] + "!!"; })(); // "hi!!"
			if( rz !== "hi!!" ) {
				null();
				throw "Incorrect result in oco#022#3: " + rz;
			}
		}
		{
			const rz = (function(){ return bar?.["someFooProp2"] + "!!"; })(); // "undefined!!"
			if( rz !== "undefined!!" ) {
				null();
				throw "Incorrect result in oco#022#4: " + rz;
			}
		}
		{
			var rz = "NOT YET RUN";
			try{
				throw foo?.["someFooProp2"];
			}catch(e){
				rz = String(e);
			} // "hi"
			if( rz !== "hi" ) { 
				throw "Incorrect result in oco#023#1: " + rz;
			}
		}
		{
			var rz = "NOT YET RUN";
			try{
				throw foo?.["someFooProp2"] + "!!";
			}catch(e){
				rz = String(e);
			} // "hi!!"
			if( rz !== "hi!!" ) { 
				throw "Incorrect result in oco#023#2: " + rz;
			}
		}
	}
	{
		{
			const rz = (foo?.["someFooProp2"] ?? "not available"); // "hi"
			if( rz !== "hi" ) {
				throw "Incorrect result in oco#120: " + rz;
			}
		}
		{
			const rz = (foo?.["someBarProp2"] ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#121: " + rz;
			}
		}
	}
}
out.println("done.");


out.print("Optional chaining operator (?.)...");
{
	let foo = { someFooProp3: "hi", tuc3 : { tuc3 : "toUpperCase" } };

	{
		{
			const rz = (foo.someFooProp3?.toUpperCase() ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#20: " + rz;
			}
		}
		{
			const rz = (foo.someBarProp3?.toUpperCase() ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#21: " + rz;
			}
		}
		{
			const rz22101 = (foo?.someFooProp3.toUpperCase.call("Hi, all", "oco#221") ?? "not available"); // "HI"
			if( rz22101 !== "HI, ALL" ) {
				throw "Incorrect result in oco#221: " + rz22101;
			}
		}
		{
			const rz22102 = (foo?.someFooProp3.toUpperCase() ?? "not available"); // "HI"
			if( rz22102 !== "HI" ) {
				throw "Incorrect result in oco#221+: " + rz22102;
			}
		}
		{
			const rz = (foo?.someFooProp3.toUpperCase("oco#222", "extra") ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#222: " + rz;
			}
		}
		{
			const rz = (foo?.someFooProp3.toUpperCase("oco#223") ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#223: " + rz;
			}
		}
		{
			const rz = (zoo?.someBarProp3.toUpperCase("oco#23") ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#23: " + rz;
			}
		}
		{
			const rz = (foo?.someFooProp3?.toUpperCase() ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				null();
				throw "Incorrect result in oco#24: " + rz;
			}
		}
		{
			const rz = (foo?.someBarProp3?.toUpperCase() ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#25: " + rz;
			}
		}
		{
			const rz = (foo["someFooProp3"]?.toUpperCase() ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#26: " + rz;
			}
		}
		{
			const rz = (foo["someBarProp3"]?.toUpperCase() ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#27: " + rz;
			}
		}
		{
			const rz = (foo["someFooProp3"]?.toUpperCase(1,2,3,4,5,6,7) ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#28: " + rz;
			}
		}
		{
			const rz = (foo["someBarProp3"]?.toUpperCase(1,2,3,4,5,6,7) ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#29: " + rz;
			}
		}
	}
	{
		{
			const rz = (foo.someFooProp3?.["toUpperCase"]() ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#30: " + rz;
			}
		}
		{
			const rz = (foo.someBarProp3?.["toUpperCase"]() ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#31: " + rz;
			}
		}
	}

	{
		{
			const rz = (foo.someFooProp3?.[foo.tuc3.tuc3]() ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#40: " + rz;
			}
		}
		{
			const rz = (foo.someBarProp3?.[foo.tuc3.tuc3]() ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#41: " + rz;
			}
		}
		{
			const rz = (function(){ return (foo.someFooProp3?.[foo.tuc3.tuc3]() ?? "not available"); })(); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#42: " + rz;
			}
		}
		{
			const rz = (function(){ return (foo.someBarProp3?.[foo.tuc3.tuc3]() ?? "not available"); })(); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#43: " + rz;
			}
		}
		{
			const rz = (function(){ return foo.someFooProp3?.[foo.tuc3.tuc3](); })() ?? "not available"; // "HI"
			if( rz !== "HI" ) {
				null(44);
				throw "Incorrect result in oco#44: " + rz;
			}
		}
		{
			const rz = (function(){ return foo.someBarProp3?.[foo.tuc3.tuc3](); })() ?? "not available"; // "not available"
			if( rz !== "not available" ) {
				null(45);
				throw "Incorrect result in oco#45: " + rz;
			}
		}
	}
	{
		{
			const rz = (foo.someFooProp3[foo.tuc3.tuc3](a,b,c) ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#50 pre-check: " + rz;
			}
		}
		{
			const rz = (foo.someFooProp3?.[foo.tuc3.tuc3](a,b,c) ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				throw "Incorrect result in oco#50: " + rz;
			}
		}
		{
			const rz = (foo.someBarProp3?.[foo.tuc3.tuc3](a,b,c) ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#51: " + rz;
			}
		}
	}

	{
		const obj = Object.create(Object.prototype, {
			"getter" : {
				get : function(){
					return this.value + "23344";
				}
			},
			"value" : {
				value : "test112"
			}
		});
		
		{
			const rz = obj.value.substring(0) + "233";
			if( rz !== "test112233" ){
				throw "Incorrect oco#getter#11: " + rz
			}
		}
		{
			const rz = obj.getter.substring(0);
			if( rz !== "test11223344" ){
				throw "Incorrect oco#getter#12: " + rz
			}
		}
		{
			const rz = obj?.value.substring(0) + "233";
			if( rz !== "test112233" ){
				throw "Incorrect oco#getter#21: " + rz
			}
		}
		{
			const rz = obj?.getter.substring(0);
			if( rz !== "test11223344" ){
				throw "Incorrect oco#getter#22: " + rz
			}
		}
		{
			const rz = obj.value?.substring(0) + "233";
			if( rz !== "test112233" ){
				throw "Incorrect oco#getter#23: " + rz
			}
		}
		{
			const rz = obj.getter?.substring(0);
			if( rz !== "test11223344" ){
				throw "Incorrect oco#getter#24: " + rz
			}
		}
		{
			const rz = obj?.value?.substring(0) + "233";
			if( rz !== "test112233" ){
				throw "Incorrect oco#getter#31: " + rz
			}
		}
		{
			const rz = obj?.getter?.substring(0);
			if( rz !== "test11223344" ){
				throw "Incorrect oco#getter#32: " + rz
			}
		}
	}

	{
		/** undefined fn value flow **/
		{
			const rzOco601 = fnReadTextFirstColumnToMapKeys?.(); // undefined
			if( rzOco601 !== undefined ){
				null.errorOco601();
				throw "Incorrect result in oco#601: " + rzOco601;
			}
		}
		{
			const rzOco602 = fnReadTextFirstColumnToMapKeys?.(entry.binaryContent, licenseMap, 15, name); // undefined
			if( rzOco602 !== undefined ){
				null.errorOco60();
				throw "Incorrect result in oco#602: " + rzOco602;
			}
		}
		
		/** defined fn value flow, simple root call **/
		{
			const fnReadTextFirstColumnToMapKeys = function(){ return 61101; };
			const rzOco611 = fnReadTextFirstColumnToMapKeys?.(); // 61101
			if( rzOco611 !== 61101 ){
				null.errorOco611();
				throw "Incorrect result in oco#611: " + rzOco611;
			}
		}
		{
			const fnReadTextFirstColumnToMapKeys = function(a){ return 61201; };
			const rzOco612 = fnReadTextFirstColumnToMapKeys?.(1); // 61201
			if( rzOco612 !== 61201 ){
				null.errorOco612();
				throw "Incorrect result in oco#612: " + rzOco612;
			}
		}
		{
			const options = { onError613c1 : function(){
				return 5;
			} };
			const rzOco613c1 = options.onError613c1?.();
			if(rzOco613c1 !== 5){
				null.errorOco613c1();
				throw "Incorrect result in oco#613c1: " + rzOco613c1;
			}
		}
		{
			const options = { onError : function(){
				return 7;
			} };
			const rzOco613c2 = options?.onError?.();
			if(rzOco613c2 !== 7){
				null.errorOco613c2();
				throw "Incorrect result in oco#613c2: " + rzOco613c2;
			}
		}
		{
			const options = { onError613c3 : function(x){
				return x == 2;
			} };
			const rzOco613c3 = options?.onError613c3?.(2);
			if(rzOco613c3 !== true){
				null.errorOco613c3();
				throw "Incorrect result in oco#613c3: " + rzOco613c3;
			}
		}
		{
			const options = { onError : function(x, y){
				return x + y == 7;
			} };
			const rzOco613c = options?.onError?.(5, 2);
			if(rzOco613c !== true){
				null.errorOco613c();
				throw "Incorrect result in oco#613c: " + rzOco613c;
			}
		}
		{
			const options = { onError613c10 : function() { return "asda"; } };
			const rzOco613c11 = options?.onError613c10?.().vvv;
			if( rzOco613c11 !== undefined ){
				null.rzOco613c11();
				throw "Incorrect result in rzOco613c11: " + rzOco613c11;
			}
			const rzOco613c12 = options?.onError613c10?.()?.charAt(1);
			if( rzOco613c12 !== "s" ){
				null.rzOco613c12();
				throw "Incorrect result in rzOco613c12: " + rzOco613c12;
			}
			
			const rzOco613c13 = options?.onError613c10?.()?.charAt?.(2);
			if( rzOco613c13 !== "d" ){
				null.rzOco613c13();
				throw "Incorrect result in rzOco613c13: " + rzOco613c13;
			}
			const rzOco613c23 = options?.onError623c20?.()?.charAt?.(2);
			if( rzOco613c23 !== undefined ){
				null.rzOco613c23();
				throw "Incorrect result in rzOco613c23: " + rzOco613c23;
			}
			const rzOco613c33 = options?.onError623c10?.()?.charNN?.(2);
			if( rzOco613c33 !== undefined ){
				null.rzOco613c33();
				throw "Incorrect result in rzOco613c33: " + rzOco613c33;
			}
			
			const rzOco613c14 = options?.onError613c10?.()?.length;
			if( rzOco613c14 !== 4 ){
				null.rzOco613c14();
				throw "Incorrect result in rzOco613c14: " + rzOco613c14;
			}
		}
		{
			const options = { onError619c10 : function() { return "aZda"; } };
			
			const fnOco619c11 = function(options){
				return options?.onError613c10?.().vvv;
			};
			const rzOco619c11 = fnOco619c11(options);
			if( rzOco619c11 !== undefined ){
				null.rzOco619c11();
				throw "Incorrect result in rzOco619c11: " + rzOco619c11;
			}
			
			const fnOco619c12 = function(options){
				return options?.onError619c10?.()?.charAt(1);
			};
			const rzOco619c12 = fnOco619c12(options);
			if( rzOco619c12 !== "Z" ){
				null.rzOco619c12();
				throw "Incorrect result in rzOco619c12: " + rzOco619c12;
			}
		}
		{
			const fnOco621c = function(a,b,c,d,e){
				return a + "-" + b + "-" + c + "-" + d + "-" + e;
			};
			const fn3 = function(x) { return x ? x : undefined;  };
			const fn4 = undefined;
			const fn5 = null;
			const fn11 = function() { return 11;  };
			const fn13 = function() { return 13;  };
			const opOco621c = { 
				fn1 : function(x) { return x ? x : undefined;  },
				fn2 : function(x) { return x ? { x : x * 2 } : undefined;  } 
			};

			const rzOco621c13 = fnOco621c(
				fn11?.(),
				fn4?.(),
				fn13?.(),
				fn5?.(),
			);
			if( rzOco621c13 !== "11-undefined-13-undefined-undefined" ){
				null.rzOco621c13();
				throw "Incorrect result in rzOco621c13: " + rzOco621c13;
			}
			
			const rzOco621c12 = fnOco621c(
				fn3?.(3),
				fn4?.(0),
				fn3?.(7),
				fn5?.(0),
			);
			if( rzOco621c12 !== "3-undefined-7-undefined-undefined" ){
				null.rzOco621c12();
				throw "Incorrect result in rzOco621c12: " + rzOco621c12;
			}
			
			const rzOco621c10 = fnOco621c(
				opOco621c.fn1?.(3),
				opOco621c.fn1?.(0),
				opOco621c.fn1NN?.(0),
				opOco621c.fn1?.(7),
			);
			if( rzOco621c10 !== "3-undefined-undefined-7-undefined" ){
				null.rzOco621c10();
				throw "Incorrect result in rzOco621c10: " + rzOco621c10;
			}
			
			const rzOco621c11 = fnOco621c(
				opOco621c?.fn1?.(3),
				opOco621c?.fn1?.(0),
				opOco621cNN?.fn1?.(0),
				opOco621c?.fn1NN?.(0),
				opOco621c?.fn1?.(7),
			);
			if( rzOco621c11 !== "3-undefined-undefined-undefined-7" ){
				null.rzOco621c11();
				throw "Incorrect result in rzOco621c11: " + rzOco621c11;
			}
		}
		{
			const fnReadTextFirstColumnToMapKeys613 = function(a, b){ return 61301; };
			const rzOco613 = fnReadTextFirstColumnToMapKeys613?.(1, 2); // 61301
			if( rzOco613 !== 61301 ){
				null.errorOco613();
				throw "Incorrect result in oco#613: " + rzOco613;
			}
		}
		{
			const entry = { binaryContent : null };
			const fnReadTextFirstColumnToMapKeys = function(a,b,c,d){ return true; };
			const rzOco612 = fnReadTextFirstColumnToMapKeys?.(entry.binaryContent, licenseMap, 15, name); // undefined
			if( rzOco612 !== true ){
				null.errorOco612();
				throw "Incorrect result in oco#612: " + rzOco612;
			}
		}
		
		
		/** defined fn value flow, access call **/
		{ // 
			const rz = (foo.someFooProp3.toUpperCase?.() ?? "not available"); // "HI"
			if( rz !== "HI" ) {
				null.errorOco621();
				throw "Incorrect result in oco#621: " + rz;
			}
		}
		{
			const rz = (foo.someFooProp3.toXpperCase?.() ?? "not available"); // "not available"
			if( rz !== "not available" ) {
				throw "Incorrect result in oco#622: " + rz;
			}
		}
	}
	{
		{
			const helper = {
				buildAuthenticateReply : function(context){
					return 11;
				}
			};
			
			const context = { helper : helper };
			const object = { helper : helper };
			
			function f(context){
				return context.helper?.buildAuthenticateReply(context) ?? this.helper?.buildAuthenticateReply(context);
			}
			
			const rzFF1 = f.call(object, context);
			if(rzFF1 !== 11){
				throw "Incorrect result in oco#FF1: " + rzFF1;
			}
	
			const rzFF2 = f.call({}, context);
			if(rzFF2 !== 11){
				throw "Incorrect result in oco#FF2: " + rzFF2;
			}
	
			const rzFF3 = f.call(object, {});
			if(rzFF3 !== 11){
				throw "Incorrect result in oco#FF3: " + rzFF3;
			}
		}
	}
}
out.println("done.");


/*******************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
***
***
***
***
***
***
*** Unsupported features will follow!!!
***
***
***
***
***
***
********************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
********************************************************************************
*******************************************************************************/


if(true){
	return "ALL SUPPORTED FEATURES 'OK'!";
}

out.print("class\new tests...");
// unsupported
{
	class Object{
	}

	class HelloWorld{
		var test = "TEST";
		function run(){
			aaa = "AAA";
			this.test = "TEST2";
		}
	}

	class Extender extends HelloWorld{
	}

	var obj1 = new HelloWorld();
	var obj2 = new HelloWorld();
	if( HelloWorld.test != "TEST" ){
		throw;
	}
	HelloWorld.run();
	if( HelloWorld.test != "TEST2" ){
		throw;
	}
	var obj3 = new HelloWorld();
	obj2.run();
	var ext1 = new Extender();
	if(obj3.test != "TEST2"){
		throw;
	}
}
out.println("done.");

out.print("this pass context/object...");
{
	class Check{
		function ff(){
			return this;
		}
	}
	
	var check = new Check();
	if( check.ff() == check ){
		throw;
	}
	
	var obj = {};
	obj.ff = check.ff;
	if( obj.ff() == obj ){
		throw;
	}
	
	var ff = check.ff;
	if( ff() ){
		throw;
	}
}
{
	class Check{
		function ff(aaa){
			return this;
		}
	}
	
	var check = new Check();
	if( check.ff(0) == null ){
		throw;
	}
	
	var obj = {};
	obj.ff = check.ff;
	if( obj.ff(0) == null ){
		throw;
	}
	
	var ff = check.ff;
	if( ff(0) != null ){
		throw;
	}
}
out.println("done.");

= 2 + 3 * 4 / 5 % 6 ^ 7;
return "all tests passed!";
