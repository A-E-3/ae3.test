// test ecma script
// version 1.1f

if(false){
	throw "if doesn't work!";
}

if(undefined){
	throw "if doesn't work!";
}

if(undefined != false){
	throw "if doesn't work!";
}

if(!Object){
	throw "Object is not defined!";
}

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
	if( undefined ){
		throw "undefined value is true!";
	}
	{
		var a;
		a = 5;
		if( !a ){
			throw "number 5 is false!";
		}
	}
	{
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
		throw "('12' + '34').length == " + c.length;
	}
	if( c.length() != 4 ){
		throw "('12' + '34').length() == " + c.length();
	}
}
out.println("done.");

out.print("Check NaN...");
{
	if(undefined == Number.NaN){
		throw "Number.NaN is undefined, value=" + Number.NaN;
	}
	if(!isNaN(Number.NaN)){
		throw "Number.NaN is not NaN";
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
if(false){
	var bbb = 1;
	with({
		aaa : 1
	}){
		var ccc = 1;
		var eee = 2, fff = 3, ggg = 4;
		var hhh, iii, jjj;
		if(bbb != 1){
			throw "parent context variable is hidden!";
		}
		if(aaa != 1){
			throw "object's context variable is hidden or not visible!";
		}
		if(ccc != 1){
			throw "local context variable is hidden or not visible!";
		}
		if(ddd == 1){
			throw "non-existent context variable is visible!";
		}
	}
}
out.println("done.");

out.print("Check 'var' statement on existing context property...");
if(false){
	if(!isNaN){
		throw "'isNaN' property should be defined!";
	}
	if(!(typeof isNaN == "function")){
		throw "'isNaN' property should contain function!";
	}
	if(isNaN == 5){
		throw "'isNaN' should not be 5 at this point yet!";
	}
	isNaN = 5;
	if(isNaN == 5){
		throw "'isNaN' should not be 5 at this point - not writable!";
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
		throw "Should (3) be equal to 2";
	}
}
{
	var count = 2;
	var open41 = true;
	var open42 = false;
	var result = count + (open41 && open42);
	if(result != 2){
		throw "Should (4) be equal to 2";
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

out.print("Method choosing (method with alternatives)...");
{
	var test = Join(['a','b','c','d'], ", ");
	if(test != "a, b, c, d"){
		throw "test=" + test;
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
		var c1 = Create.map( description = "false" );
		var cKeys = Join(c1.keySet(), ", "); // to see keys in error message, if any
		var c11 = c1.description;
		if(c11 != "false"){
			throw "c11=" + c11;
		}
	}
	{
		var c2 = Create.map( description = "false", hidden = "true" );
		var cKeys = Join(c2.keySet(), ", "); // to see keys in error message, if any
		var c21 = c2.description;
		var c22 = c2.hidden;
		if(c21 != "false" || c22 != "true"){
			throw "c21=" + c21 + ", c22=" + c22;
		}
	}
	{
		var c3 = Create.map( description = "false", hidden = "true", file = "undefined" );
		var cKeys = Join(c3.keySet(), ", "); // to see keys in error message, if any
		var c31 = c3.description;
		var c32 = c3.hidden;
		var c33 = c3.file;
		if(c31 != "false" || c32 != "true" || c33 != "undefined"){
			throw "c31=" + c31 + ", c32=" + c32 + ", c33=" + c33;
		}
	}
	{
		var m = Create.map();
		var t = m.toString();
		if( ("a" + m) != "a" + t){
			throw "'a" + t + "' expected, but: " + ("a" + m);
		}
	}
	{
		var m = Create.map( null );
		var t = m.toString();
		if( ("a" + m) != "a" + t){
			throw "'a" + t + "' expected, but: " + ("a" + m);
		}
	}
	{
		/**
		 * extra things
		 */
		var maps = [Create.map(), Create.mapFor(), {}];
		for(var map in maps){
			if(!Object.isMap(map)){
				throw "Incorrect!";
			}
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
			if(HashFilled(map)){
				throw "Incorrect, keyset: " + map.size();
			}
			if(Object.Filled(map)){
				throw "Incorrect, keyset: " + map.size();
			}
		}
		var maps = [Create.map(m = 5), Create.mapFor(m = 5), {m : 5}, {"m" : 5}];
		for(var map in maps){
			if(map.size() != 1){
				throw "Incorrect, size=" + map.size();
			}
			if(!Object.isMap(map)){
				throw "Incorrect!";
			}
			if(!HashFilled(map)){
				throw "Incorrect!";
			}
			if(!Object.isFilled(map)){
				throw "Incorrect!";
			}
			if(map.m != 5){
				throw "Incorrect, value=" + map.m;
			}
		}
		var map = Create.map(m = 6, k = 7);
		if(map.size() != 2){
			throw "Incorrect, size=" + map.size();
		}
		if(!Object.isMap(map)){
			throw "Incorrect!";
		}
		if(!HashFilled(map)){
			throw "Incorrect!";
		}
		if(!Object.isFilled(map)){
			throw "Incorrect!";
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
	if( isFinite(-Infinity) ){
		throw "isFinite fail";
	}
	/** sorry unary + is broken %)
	if( isFinite(+Infinity) ){
		throw "isFinite fail";
	}
	*/
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

out.print("Constructors...");
{
	{
		import java.lang.Double;
		var x = Double.valueOf(5);
		if(!x){
			throw "Constructor returned null or undefined object!";
		}
	}
	{
		var x = new Object();
		if(!x){
			throw "Constructor returned null or undefined object!";
		}
	}
}
out.println("done.");

out.println("BOR / BAND extended with maps...");
{
	var change = Create.map( description = false, hidden = true );
	if((change.description || change.hidden || flag) && recFile){
		throw "Incorrect IF flow!";
	}
}
{
	var change = Create.map( description = true, hidden = false );
	if((change.description || change.hidden || flag) && recFile){
		throw "Incorrect IF flow!";
	}
}
{
	var change = Create.map( description = false, hidden = false );
	var flag = true;
	if((change.description || change.hidden || flag) && recFile){
		throw "Incorrect IF flow!";
	}
}
{
	var recFile = true;
	var change = Create.map( description = false, hidden = true );
	if((change.description || change.hidden || flag) && recFile){
		// ignore
	}else{
		throw "Incorrect IF flow!";
	}
}
{
	var recFile = true;
	var change = Create.map( description = true, hidden = false );
	if((change.description || change.hidden || flag) && recFile){
		// ignore
	}else{
		throw "Incorrect IF flow!";
	}
}
{
	var recFile = true;
	var change = Create.map( description = false, hidden = false );
	var flag = true;
	if((change.description || change.hidden || flag) && recFile){
		// ignore
	}else{
		throw "Incorrect IF flow!";
	}
}
{
	var a = Create.map();
	if(a.aaa != undefined){
		throw "Should be undefined (1), but: " +  a.aaa;
	}
	if(a.aaa !== undefined){
		throw "Should be undefined (1), but: " +  a.aaa;
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
	if(false === 0) {
		throw;
	}
	if(0 !== 0) {
		throw;
	}
	if("aa" !== "aa") {
		throw;
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

out.print("new java object test...");
{
	if(Double){
		throw "Not yet!";
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

out.print("OBJECT check tests...");
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
	if(false){
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
		var a = Create.map();
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

	if( Create.list().toString() != "" ){
		throw "Expected that empty list converted to string will be an empty string, but: " + Create.list().toString();
	}
	
	/*
	if( Create.list().toSource() != "[]" ){
		throw "Expected that empty list converted to source will be [], but: " + Create.list().toSource();
	}
	*/
	
	if( Create.list("aaa").toString() != "aaa" ){
		throw "Expected that list converted to string will be 'aaa', but: " + Create.list("aaa").toString();
	}
	
	if( Textual([]) != "[]" ){
		throw "Expected that empty array converted to string by Textual method will be [], but: " +Textual(Create.list());
	}
	
	if( [] + "[]" != "[]" ){
		throw "Expected that [] concatenated with '[]' string will be '[]', but: " + ([] + "[]");
	}
}
out.println("done.");

out.print("SACCESS test...");
{
	{
		var imap = Create.map();
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
			(brr = Create.map())["aaa" + Create.list("[]")] += 55;
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
	var imap = Create.map();
	var type = Array.isArray(imap) ? 'list' : (Object.isMap(imap) ? 'map' : false);
	if(type != "map"){
		throw "Should be equals to 'map'";
	}
}
{
	var imap = Create.list();
	var type = Array.isArray(imap) ? 'list' : (Object.isMap(imap) ? 'map' : false);
	if(type != "list"){
		throw "Should be equals to 'list'";
	}
}
{
	var imap = "aaaaa";
	var type = Array.isArray(imap) ? 'list' : (Object.isMap(imap) ? 'map' : false);
	if(type != false){
		throw "Should be equals to FALSE";
	}
}
{
	// kinda more complex
	var data = Create.map();
	var resultMaps = !data.resultMaps ? Create.map(trash = Create.map(), new = Create.map()) : data.resultMaps;
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
			throw "error != check1";
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
		var a = Create.list();
		var b = Create.list();
		var c = Create.list();
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
				substr(1);
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
			function fn(a){
				return this * a;
			}
			var result = fn.call(2, 2);
			if(result != 2 * 2){
				throw (2 * 2) + " expected, got: " + result;
			}
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
				substr(1);
				return true;
			}catch(error){
				return false;
			}
			return true;
		}
	
		{
			var result;
			try{
				result = fNormalCatch();
			}catch(e){
				throw "Error should stay in function (21)!";
			}
			if(result){
				throw "Should be catched!";
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
			throw "Error should stay in function (22)!";
		}
	}
	{
		function fEmptyCatch(){
			try{
				// this line should produce error!
				substr(1);
			}catch(error){
			}
			return true;
		}
	
		try{
			fEmptyCatch();
		}catch(e){
			throw "Error should stay in function (23)!";
		}
	}

	{
		function fFunctionCatch(){
			// this line should produce error!
			substr(1);
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

out.print("'typeof' keyword test...");
{
	if(typeof aaa != "undefined"){
		throw "typeof undefined object should return string 'undefined'";
	}
	if(typeof undefined != "undefined"){
		throw "typeof undefined object should return string 'undefined'";
	}
	if(typeof null != "object"){
		throw "typeof null object should return string 'object'";
	}
	var number = 5;
	if(typeof number != "number"){
		throw "typeof variable containing a number should return string 'number'";
	}
	if(typeof 5 != "number"){
		throw "typeof 5 should return string 'number'";
	}
	var boolean = true;
	if(typeof boolean != "boolean"){
		throw "typeof variable containing a boolean value should return string 'boolean'";
	}
	if(typeof true != "boolean"){
		throw "typeof true should return string 'boolean'";
	}
	if(typeof false != "boolean"){
		throw "typeof false should return string 'boolean'";
	}
	if(typeof (number > 1) != "boolean"){
		throw "typeof boolean expression result should return string 'boolean'";
	}
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

out.print("NAMED ARGUMENTS...");
{
	var map = Create.map(
		trash = Create.map(),
		new = Create.map()
	);
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
}
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
if(false){ 
	// TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	{
		var test1 = false;
		++ test1;
		++ test1;
		if( test1 != 2 ){
			throw "increment pre doesn't work!";
		}
		-- test1;
		-- test1;
		if( test1 ){
			throw "decrement pre doesn't work!";
		}
	}
	{
		var test1 = 0;
		++ test1;
		++ test1;
		if( test1 != 2 ){
			throw "increment pre doesn't work!";
		}
		-- test1;
		-- test1;
		if( test1 ){
			throw "decrement pre doesn't work!";
		}
	}
}
out.println("done.");

out.print("OUTPUT...");
{
	var test4;
	$output( test ){
		= "11111";
		$output( null ){
			= "22222";
		}
		= "33333";
		$output( test4 ){
			= "44444";
		}
		= "55555";
	}

	if( test != "111113333355555"){
		throw "OUTPUT fail!";
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
	function f(){
		return this;
	}
	if( f() ){
		throw "No this expected for simple call, but: " + f();
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
{
	function f(aaa){
		return this;
	}
	if( f() ){
		throw "No this expected for simple call, but: " + f();
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
if(false){
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
		switch(c){
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
	var object = Create.map();
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
out.println("done.");

out.print("hard access...");
{
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
		userState : 55
	};
	
	managerMap[Record.managerId].data[cUA].userStates[Record.userState] = 0;
	if(managerMap.mmm.data.zzz.userStates[55] !== 0){
		throw "value is not strictly 0 but: " + managerMap.mmm.data.zzz.userStates[55];
	}
	
	managerMap[Record.managerId].data[cUA].userStates[Record.userState] = managerMap[Record.managerId].data[cUA].userStates[Record.userState] + 1;
	if(managerMap.mmm.data.zzz.userStates[55] !== 1){
		throw "value is not strictly 1 but: " + managerMap.mmm.data.zzz.userStates[55];
	}
	
	managerMap[Record.managerId].data[cUA].userStates[Record.userState] = managerMap[Record.managerId].data[cUA].userStates[Record.userState] + 1;
	if(managerMap.mmm.data.zzz.userStates[55] !== 2){
		throw "value is not strictly 2 but: " + managerMap.mmm.data.zzz.userStates[55];
	}
	
	var test = managerMap[Record.managerId].data[cUA].userStates;
	test[Record.userState] = test[Record.userState] + 1;
	if(managerMap.mmm.data.zzz.userStates[55] !== 3){
		throw "value is not strictly 3 but: " + managerMap.mmm.data.zzz.userStates[55];
	}
	managerMap[Record.managerId].data[cUA].userStates[Record.userState] += 2;
	if(managerMap.mmm.data.zzz.userStates[55] != 5){
		throw "value is not 5 but: " + managerMap.mmm.data.zzz.userStates[55];
	}
	
	managerMap[Record.managerId].data[cUA].userStates[Record.userState] ++;
	if(managerMap.mmm.data.zzz.userStates[55] != 6){
		throw "value is not 6 but: " + managerMap.mmm.data.zzz.userStates[55];
	}
	
	managerMap[Record.managerId].data[cUA].userStates[Record.userState] += Record.managerId;
	if(managerMap.mmm.data.zzz.userStates[55] != '6mmm'){
		throw "value is not '6mmm' but: " + managerMap.mmm.data.zzz.userStates[55];
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
out.println("done.");

out.print("WHILE test (2)...");
{
	var parent = 1;
	while(true){
		parent = parent + 1;
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
}
out.println("done.");

out.print("ARRAY check tests...");
{
	if(!Array){
		throw "no global Array object accessible!";
	}
	if(!Array.prototype){
		throw "no Array.prototype accessible!";
	}
	if(!Array.prototype.push){
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
}
out.println("done.");

out.print("for loop test...");
{
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
	/**
	 * Not meant to work yet
	 */
	if(false){
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
	var list = Create.list();
	list.add(f);
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
	var list = [Array.isArray, Object.size];
	if( list[0](null) ){
		throw;
	}
	if( !list[0]( [1,2,3,4,5,6] ) ){
		throw;
	}
	if( !list[1]( { a : 1, b : 2, c : 3 } ) ){
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

out.print("array loops...");
{
	{
		var test = [ 0, 1, 2, 3, 4, 5, 6, 7, 8 ];
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
		var test = [ 0, 1, 2, 3, 4, 5, 6, 7, 8 ];
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
		type = Int( param.substring(param.indexOf("/") + 1), type );
		check = param.substring(0, param.indexOf("/"));
		if( check != "10" ){
			throw "substring is broken, check should be '10', but: " + check;
		}
		size = Int( param.substring(0, param.indexOf("/")), size );
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

out.print("reflection getXXX preference over property...");
{
	/**
	 * 	var FieldsLookup = { b : { a : 'success' }, class : { a : 'success' } };
	 * 		!!!!! is not working, due to: Exception in thread "main" java.lang.RuntimeException: Error while compiling: FieldsLookup={ b:{ a:@428 },
	 *		Caused by: java.lang.IllegalStateException: Invalid compile-time token: {
	 *			at ru.myx.ae3.exec.ProgramAssembly.addExpression(ProgramAssembly.java:316)
	 *			at ru.myx.ae3.exec.ProgramAssembly.addExpression(ProgramAssembly.java:311)
	 *			at ru.myx.ae3.exec.ProgramAssembly.addExpression(ProgramAssembly.java:142)
	 */
	var check = { a : 'success' };
	var FieldsLookup = { "b" : check, "class" : check };
	var Record = { b : 'a', "class" : 'a' };
	for(var Current in ['b','class']){
		var value = FieldsLookup[Current].get(Record[Current]);
		if(value != 'success'){
			throw "key is '" + Current + "', value is not success: " + value;
		}
	}
}
out.println("done.");

out.print("Nested class access...");
{
	import ae3test.ecma.classes.NestedClassContainer;
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
	import ae3test.ecma.classes.EnumTest;
	out.print(".");
	if(!EnumTest){
		throw "Test case is not accessible!";
	}
	out.print(".");
	if(!EnumTest.VALUE1){
		throw "Cannot access existing enum constant!";
	}
	out.print(".");
	if(EnumTest.VALUE_NON_EXISTENT){
		throw "Non-existing enum constant returns non-null value!";
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

out.print("reserved words...");
{
	for(var test in ["{ class : 'aaa' }", "( class = a )"]){
		try{
			eval( '(' + test + ')' );
		}catch(t){
			continue;
		}
		throw "Should fail before!";
	}
}
out.println("done.");

out.print("'delete' keyword test...");
// unsupported
{
	{
		b = "test";
		delete b;
		if(b){
			throw "global variable should be deleted!!!";
		}
	}
	{
		var a = Create.map();
		a.field1 = "test";
		delete a.field;
		if(a.field){
			throw "field should be deleted!!!";
		}
		delete a;
		if(!a){
			throw "locally declared variable should not be deleted!!!";
		}
	}
}
out.println("done.");

out.print("INLINE FUNCTION...");
// unsupported
{
	var func = function(){return 5;};
	
	if( 5 != func() ){
		throw "inline function doesn't work!";
	}
}
out.println("done.");


{
	{
		var testCheck = 0 + 1 + 2 + 3 + 4 + 5;
		var testResult = 0;
		var testArray = (1,2,3,4,5);
		for( var current in testArray ){
			testResult += current;
		}
		if( testResult != testCheck ){
			throw;
		}
		var counter = testArray.size() * testArray.size();
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
		var testMatrix = ( (1,0), (0,1), (0,0), (1,1), (-1,0), (0,-1), (-1,-1) );
		var counter;
		for( var i in testMatrix ){
			for( var j in i){
			 counter += j;
			}
		}
		if(counter){
			throw;
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
	for( var i = 10; i >=0; i-- ){
		var temp1;
		var temp2;
		
		if( i > 5 ){
			continue;
		}
		for( var j = 10; i >=0; i-- ){
			if( j > 5 ){
				continue;
			}
			if( j == 2 ){
				break;
			}
		}
		if( i == 2 ){
			break;
		}
	}
	
	var arr = (10,9,8,7,6,5,4,3,2,1,0);

	for( var i in arr ){
		var temp1;
		var temp2;
		
		if( i > 5 ){
			continue;
		}
		for( var j in arr ){
			if( j > 5 ){
				continue;
			}
			if( j == 2 ){
				break;
			}
		}
		if( i == 2 ){
			break;
		}
	}
	

	if(false){
	var check = 1;	
	var fieldSize = 3;
	for(;;){
		var cx = check ++;
		var cy = check ++;
		if(cx >= 0 && cy >= 0 && cx < fieldSize && cy < fieldSize){
			var idx = cx + cy * fieldSize;
			var value = idx * 2;
			value = 0;
			if(value > 0 && compare == (value & 1)){
				field[ idx ] = (compare == 0) ? 4 : 3;
				shift ++;
			}
		}else{
			break;
		}
	}
	}
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
	if( check.ff() == null ){
		throw;
	}
	
	obj.ff = check.ff;
	if( obj.ff() == null ){
		throw;
	}
	
	var ff = check.ff;
	if( ff() != null ){
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
