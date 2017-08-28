package com.crx.rainyhills.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.crx.rainyhills.entity.Surface;
import com.crx.rainyhills.repository.SurfaceRepository;
import com.crx.rainyhills.util.Resources;

import br.com.rainyhills.model.dto.SurfaceDTO;
import br.com.rainyhills.model.service.SurfaceServiceLocal;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SurfaceRegistrationTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(Surface.class, SurfaceRepository.class, Resources.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	SurfaceServiceLocal surfaceRegistration;

	@Inject
	Logger log;

	@Test
	public void testRegister() throws Exception {
		SurfaceDTO surfaceDTO = new SurfaceDTO();
		surfaceDTO.setValues("32412");
		surfaceDTO.setVolume(2);
		surfaceRegistration.register(surfaceDTO);
		assertNotNull(surfaceDTO.getId());
		log.info(surfaceDTO.getValues() + " was persisted with id " + surfaceDTO.getId());
	}
}
