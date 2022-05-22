package lostworlds.client.books.tyrannibook.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import lostworlds.client.books.tyrannibook.client.data.SectionData;
import lostworlds.client.books.tyrannibook.client.repository.TyrannobookRepository;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.resources.ResourceLocation;

public class FileRepository extends TyrannobookRepository {
	public final String location;

	public FileRepository(String location) {
		this.location = location;
	}

	@Override
	public List<SectionData> getSections() {
		return new ArrayList<>(Arrays.asList(TyrannobookLoader.GSON.fromJson(this.resourceToString(this.getResource(this.getResourceLocation("index.json"))), SectionData[].class)));
	}

	@Override
	public ResourceLocation getResourceLocation(@Nullable String path, boolean safe) {
		if (path == null) {
			return safe ? new ResourceLocation("") : null;
		}

		if (!path.contains(":")) {
			String langPath = null;

			if (Minecraft.getInstance().getLanguageManager() != null && Minecraft.getInstance().getLanguageManager().getSelected() != null) {
				langPath = Minecraft.getInstance().getLanguageManager().getSelected().getCode();
			}

			String defaultLangPath = "en_us";

			ResourceLocation res;

			if (langPath != null) {
				res = new ResourceLocation(this.location + "/" + langPath + "/" + path);
				if (this.resourceExists(res)) {
					return res;
				}
			}
			res = new ResourceLocation(this.location + "/" + defaultLangPath + "/" + path);
			if (this.resourceExists(res)) {
				return res;
			}
			res = new ResourceLocation(this.location + "/" + path);
			if (this.resourceExists(res)) {
				return res;
			}
		} else {
			ResourceLocation res = new ResourceLocation(path);
			if (this.resourceExists(res)) {
				return res;
			}
		}

		return safe ? new ResourceLocation("") : null;
	}

	@Override
	public Resource getResource(@Nullable ResourceLocation loc) {
		if (loc == null) {
			return null;
		}

		try {
			return Minecraft.getInstance().getResourceManager().getResource(loc);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public boolean resourceExists(@Nullable ResourceLocation location) {
		if (location == null) {
			return false;
		}
		return Minecraft.getInstance().getResourceManager().hasResource(location);
	}

	@Override
	public String resourceToString(@Nullable Resource resource, boolean skipComments) {
		if (resource == null) {
			return "";
		}

		try {
			Iterator<String> iterator = IOUtils.readLines(resource.getInputStream(), StandardCharsets.UTF_8).iterator();
			StringBuilder builder = new StringBuilder();

			boolean isLongComment = false;

			while (iterator.hasNext()) {
				String s = iterator.next().trim() + "\n";

				if (skipComments) {
					if (isLongComment) {
						if (s.endsWith("*/")) {
							isLongComment = false;
						}
						continue;
					} else {
						if (s.startsWith("/*")) {
							isLongComment = true;
							continue;
						}
					}
					if (s.startsWith("//")) {
						continue;
					}
				}

				builder.append(s);
			}

			return builder.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
}
