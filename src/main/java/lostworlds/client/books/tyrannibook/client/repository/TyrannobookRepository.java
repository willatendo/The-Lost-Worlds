package lostworlds.client.books.tyrannibook.client.repository;

import java.util.List;

import javax.annotation.Nullable;

import lostworlds.client.books.tyrannibook.client.data.SectionData;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;

public abstract class TyrannobookRepository {
	public static final TyrannobookRepository DUMMY = new DummyRepository();

	public abstract List<SectionData> getSections();

	public ResourceLocation getResourceLocation(@Nullable String path) {
		return this.getResourceLocation(path, false);
	}

	public abstract ResourceLocation getResourceLocation(@Nullable String path, boolean safe);

	public abstract IResource getResource(@Nullable ResourceLocation location);

	public boolean resourceExists(@Nullable String location) {
		if (location == null) {
			return false;
		}

		return this.resourceExists(new ResourceLocation(location));
	}

	public abstract boolean resourceExists(@Nullable ResourceLocation location);

	public String resourceToString(@Nullable IResource resource) {
		return this.resourceToString(resource, true);
	}

	public abstract String resourceToString(@Nullable IResource resource, boolean skipComments);
}
