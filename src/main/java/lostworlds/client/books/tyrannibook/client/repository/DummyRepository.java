package lostworlds.client.books.tyrannibook.client.repository;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import lostworlds.client.books.tyrannibook.client.data.SectionData;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class DummyRepository extends TyrannobookRepository {
	@Override
	public List<SectionData> getSections() {
		return Collections.emptyList();
	}

	@Override
	public ResourceLocation getResourceLocation(@Nullable String path, boolean safe) {
		return null;
	}

	@Override
	public IResource getResource(@Nullable ResourceLocation loc) {
		return null;
	}

	@Override
	public boolean resourceExists(@Nullable ResourceLocation location) {
		return false;
	}

	@Override
	public String resourceToString(@Nullable IResource resource, boolean skipComments) {
		return "";
	}
}
