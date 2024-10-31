package github.yeori.morpheme_server.domain.morpheme;

import org.springframework.web.bind.annotation.RestController;
import github.yeori.morpheme_server.domain.Tag;
import github.yeori.morpheme_server.domain.morpheme.entity.MorphemeDto;
import github.yeori.morpheme_server.domain.morpheme.entity.TextInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/mpm")
public class MorphemeController {

  final MorphemeService morphemeService;
  final Map<String, String> tagMap;

  public MorphemeController(MorphemeService morphemeService) {
    this.morphemeService = morphemeService;
    this.tagMap = Tag.toMap();
  }

  @GetMapping("/tags")
  public Object listTags() {
    return this.tagMap;
  }

  @PostMapping()
  public Object analyizeText(@RequestBody TextInput textInput) {
    MorphemeDto dto = this.morphemeService.analyizeText(textInput);
    return dto;
  }

}
