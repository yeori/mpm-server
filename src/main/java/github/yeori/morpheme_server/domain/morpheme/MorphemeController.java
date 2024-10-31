package github.yeori.morpheme_server.domain.morpheme;

import org.springframework.web.bind.annotation.RestController;
import github.yeori.morpheme_server.domain.morpheme.entity.MorphemeDto;
import github.yeori.morpheme_server.domain.morpheme.entity.TextInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class MorphemeController {

  final MorphemeService morphemeService;

  public MorphemeController(MorphemeService morphemeService) {
    this.morphemeService = morphemeService;
  }

  @PostMapping("/mpm")
  public Object analyizeText(@RequestBody TextInput textInput) {
    MorphemeDto dto = this.morphemeService.analyizeText(textInput);
    return dto;
  }

}
