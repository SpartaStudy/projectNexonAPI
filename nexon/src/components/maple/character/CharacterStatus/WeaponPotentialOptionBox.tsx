import styled from "styled-components";
import WeaponOptionItem from "./WeaponOptionItem";
import DashedLine from "../../../common/DashedLine";
import OptionTitle from "../../../../style/OptionTitle";

const StyledList = styled.ul`
  width: 100%;
  margin: 0;
  padding: 8px 16px;
  list-style: none;
`;

interface Props {
  title: string;
  grade: string | null | undefined;
  potential2: string | null | undefined;
  potential3: string | null | undefined;
  potential1: string | null | undefined;
}

const WeaponPotentialOptionBox: React.FC<Props> = ({ title, grade, potential1, potential2, potential3 }) => {
  if (!potential1 && !potential2 && !potential3) return null;

  const isAdditional = title !== "잠재옵션";

  return (
    <>
      <DashedLine />
      <StyledList>
        <OptionTitle
          title={title}
          highlight={grade ? grade : "#000"}
          logo={`${process.env.PUBLIC_URL}/assets/${isAdditional ? "letter-r.png" : "letter-e.png"}`}
        />
        {potential1 && <WeaponOptionItem desc={potential1} />}
        {potential2 && <WeaponOptionItem desc={potential2} />}
        {potential3 && <WeaponOptionItem desc={potential3} />}
      </StyledList>
    </>
  );
};

export default WeaponPotentialOptionBox;

