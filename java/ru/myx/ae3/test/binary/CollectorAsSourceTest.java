package ru.myx.ae3.test.binary;

import java.nio.ByteBuffer;

import ru.myx.ae3.act.Act;
import java.util.function.Function;
import ru.myx.ae3.binary.TransferBuffer;
import ru.myx.ae3.binary.TransferTarget;
import ru.myx.ae3.exec.ExecNonMaskedException;
import ru.myx.ae3.exec.ExecProcess;

class CollectorAsSourceTest implements TransferTarget {
	
	private static void log(final String x) {
		System.out.println( ">>> >>> BIN >>> CST: " + x );
	}
	
	long	rx	= 0;
	
	@Override
	public void abort(final String reason) {
		CollectorAsSourceTest.log( "abort: " + reason );
	}
	
	@Override
	public boolean absorb(final int i) {
		this.rx += 1;
		CollectorAsSourceTest.log( "absorb: " + i );
		return true;
	}
	
	@Override
	public boolean absorbArray(final byte[] array, final int off, final int len) {
		this.rx += len;
		CollectorAsSourceTest.log( "absorbArray: " + array + ", " + off + ", " + len );
		return true;
	}
	
	@Override
	public boolean absorbBuffer(final TransferBuffer buffer) {
		this.rx += buffer.remaining();
		CollectorAsSourceTest.log( "absorbBuffer: " + buffer.remaining() );
		return true;
	}
	
	@Override
	public boolean absorbNio(final ByteBuffer buffer) {
		this.rx += buffer.remaining();
		CollectorAsSourceTest.log( "absorbNio: " + buffer.remaining() );
		return true;
	}
	
	@Override
	public void close() {
		CollectorAsSourceTest.log( "close" );
	}
	
	@Override
	public <A, R> boolean enqueueAction(final ExecProcess ctx, final Function<A, R> function, final A argument) {
		CollectorAsSourceTest.log( "action: " + function + ", " + argument );
		try {
			Act.run( ctx, function, argument );
		} catch (final ExecNonMaskedException e) {
			throw e;
		} catch (final Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public void force() {
		CollectorAsSourceTest.log( "force" );
	}
	
}
