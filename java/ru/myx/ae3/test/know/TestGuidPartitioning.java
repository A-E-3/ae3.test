package ru.myx.ae3.test.know;

import java.util.Arrays;

import ru.myx.ae3.Engine;
import ru.myx.ae3.base.Base;
import ru.myx.ae3.know.Guid;
import ru.myx.sapi.RandomSAPI;

/**
 * @author myx
 * 
 */
public class TestGuidPartitioning {
	
	private static void check(final int start, final int count) {
		System.out.println( ">>> [" + start + ".." + (start + count) + "] (" + count + ") ---------------------------" );
		final int[] buckets02i = new int[02];
		final int[] buckets07i = new int[07];
		final int[] buckets16i = new int[16];
		final int[] buckets23i = new int[23];
		final int[] buckets02s = new int[02];
		final int[] buckets07s = new int[07];
		final int[] buckets16s = new int[16];
		final int[] buckets23s = new int[23];
		final int[] buckets02S = new int[02];
		final int[] buckets07S = new int[07];
		final int[] buckets16S = new int[16];
		final int[] buckets23S = new int[23];
		for (int i = start, s10 = start, s78 = start, j = count; j > 0; j--) {
			{
				final double parameter = Guid.forJavaInteger( i++ ).getPartitioningParameter();
				buckets02i[(int) Math.floor( parameter * 02 )]++;
				buckets07i[(int) Math.floor( parameter * 07 )]++;
				buckets16i[(int) Math.floor( parameter * 16 )]++;
				buckets23i[(int) Math.floor( parameter * 23 )]++;
			}
			{
				final double parameter = Guid.forString( RandomSAPI.formattedString( "DDD" ) + String.valueOf( s10++ ) )
						.getPartitioningParameter();
				buckets02s[(int) Math.floor( parameter * 02 )]++;
				buckets07s[(int) Math.floor( parameter * 07 )]++;
				buckets16s[(int) Math.floor( parameter * 16 )]++;
				buckets23s[(int) Math.floor( parameter * 23 )]++;
			}
			{
				final double parameter = Guid.forString( RandomSAPI.formattedString( "MMM" ) + String.valueOf( s78++ ) )
						.getPartitioningParameter();
				buckets02S[(int) Math.floor( parameter * 02 )]++;
				buckets07S[(int) Math.floor( parameter * 07 )]++;
				buckets16S[(int) Math.floor( parameter * 16 )]++;
				buckets23S[(int) Math.floor( parameter * 23 )]++;
			}
		}
		
		System.out.print( ">>> int:" );
		System.out.println();
		
		TestGuidPartitioning.dump( start, count, buckets02i );
		TestGuidPartitioning.dump( start, count, buckets07i );
		TestGuidPartitioning.dump( start, count, buckets16i );
		TestGuidPartitioning.dump( start, count, buckets23i );
		
		System.out.print( ">>> str:" );
		System.out.println();
		
		TestGuidPartitioning.dump( start, count, buckets02s );
		TestGuidPartitioning.dump( start, count, buckets07s );
		TestGuidPartitioning.dump( start, count, buckets16s );
		TestGuidPartitioning.dump( start, count, buckets23s );
		
		System.out.print( ">>> STR:" );
		System.out.println();
		
		TestGuidPartitioning.dump( start, count, buckets02S );
		TestGuidPartitioning.dump( start, count, buckets07S );
		TestGuidPartitioning.dump( start, count, buckets16S );
		TestGuidPartitioning.dump( start, count, buckets23S );
		System.out.println();
	}
	
	private static void dump(final int start, final int count, final int[] buckets) {
		for (final int result : buckets) {
			System.out.print( " " + result );
		}
		System.out.print( "  >>  " );
		Arrays.sort( buckets );
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (final int result : buckets) {
			if (result < min) {
				min = result;
			}
			if (result > max) {
				max = result;
			}
		}
		final double percentile = ((double) max - min) * /* 1000 */buckets.length * 10 / (buckets.length * count);
		System.out.print( "    " + Base.toFixed( percentile, 4 ) + "%%" );
		assert count <= 256 || 0 == (int) percentile : "Too much!";
		System.out.println();
	}
	
	/**
	 * @param args
	 * @throws Throwable
	 */
	public static final void main(final String[] args) throws Throwable {
		Engine.createGuid();
		
		TestGuidPartitioning.check( 0, 16 );
		TestGuidPartitioning.check( 2, 16 );
		TestGuidPartitioning.check( 0, 64 );
		TestGuidPartitioning.check( 96, 64 );
		TestGuidPartitioning.check( 256, 64 );
		TestGuidPartitioning.check( 65536, 64 );
		TestGuidPartitioning.check( Integer.MAX_VALUE - 65536, 64 );
		TestGuidPartitioning.check( 0, 256 );
		
		TestGuidPartitioning.check( 0, 1024 );
		TestGuidPartitioning.check( Integer.MAX_VALUE - 1024, 1024 );
		TestGuidPartitioning.check( Integer.MIN_VALUE, 1024 );
		
		TestGuidPartitioning.check( 0, 65536 );
		TestGuidPartitioning.check( Integer.MAX_VALUE - 65536, 65536 );
		TestGuidPartitioning.check( Integer.MIN_VALUE, 65536 );
		
		TestGuidPartitioning.check( 0, 2 * 65536 );
		TestGuidPartitioning.check( Integer.MAX_VALUE - 2 * 65536, 2 * 65536 );
		TestGuidPartitioning.check( Integer.MIN_VALUE, 2 * 65536 );
		
		TestGuidPartitioning.check( 0, 8 * 65536 );
		TestGuidPartitioning.check( Integer.MAX_VALUE - 8 * 65536, 8 * 65536 );
		TestGuidPartitioning.check( Integer.MIN_VALUE, 8 * 65536 );
	}
}
