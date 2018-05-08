package com.rmit.jmoss;

import com.rmit.jmoss.exceptions.FilmNameTooShortException;
import com.rmit.jmoss.models.*;
import com.rmit.jmoss.util.DataReadWrite;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class SearchMovieByNameTest extends TestCase {

	@Test
	public void movieExists () throws FilmNameTooShortException {
		Collection<Screening> movies = JMossService.getInstance().searchByFilmName("Ready");
		assertEquals(movies.isEmpty(), false);
	}

	@Test
	public void movieNotExists () throws FilmNameTooShortException {
		Collection<Screening> movies = JMossService.getInstance().searchByFilmName("dsads001");
		assertEquals(movies.isEmpty(), true);
	}
}
