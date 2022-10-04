package megaptera.makaoGift.Validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(
    {ValidationGroups.NotEmptyGroup.class ,
    ValidationGroups.PatternCheckGroup.class})
public interface ValidationSequence {
}
