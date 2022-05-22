package lostworlds.client.books.tyrannibook.client.repository;

import java.util.List;

import javax.annotation.Nullable;

import lostworlds.client.books.tyrannibook.client.data.SectionData;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.resources.ResourceLocation;

public abstract class TyrannobookRepository {
	public static final TyrannobookRepository DUMMY = new DummyRepository();

	public abstract List<SectionData> getSections();

	public ResourceLocation getResourceLocation(@Nullable String path) {
		return this.getResourceLocation(path, false);
	}

	public abstract ResourceLocation getResourceLocation(@Nullable String path, boolean safe);

	public abstract Resource getResource(@Nullable ResourceLocation location);

	public boolean resourceExists(@Nullable String location) {
		if (location == null) {
			return false;
		}

		return this.resourceExists(new ResourceLocation(location));
	}

	public abstract boolean resourceExists(@Nullable ResourceLocation location);

	public String resourceToString(@Nullable Resource resource) {
		return this.resourceToString(resource, true);
	}

	public abstract String resourceToString(@Nullable Resource resource, boolean skipComments);
}
