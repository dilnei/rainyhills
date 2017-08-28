package com.crx.rainyhills.test;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.crx.rainyhills.entity.Surface;

import br.com.rainyhills.model.service.SurfaceServiceLocal;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SurfaceCalculationTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(Surface.class).addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	SurfaceServiceLocal surfaceService;

	@Inject
	Logger log;

	@Test
	public void testCalculationNumbersStr() throws Exception {
		Integer expected = 8;
		Integer value = surfaceService.calculatesUnit("411023");
		assertNotNull(value);
		assertEquals("The values match", expected, value);
		log.info("The value expected: " + expected + " The original value : " + value);
	}
}
