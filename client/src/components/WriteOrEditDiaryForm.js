import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import TextEditor from '../components/TextEditor';
import BorderButton from '../components/common/BorderButton';
import ImageInput from '../components/common/ImageInput';
import ConfirmModal from './common/ConfirmModal';
import styled from 'styled-components';
import {
  //  addDiary,
  getBookList,
} from '../lib/axios';

const WriteOrEditDiaryForm = (props) => {
  const [fileURL, setFileURL] = useState(null);
  const [booklist, setBooklist] = useState([]);
  const [confirm, setConfirm] = useState(false);

  const navigate = useNavigate();

  const {
    register,
    setValue,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm({});

  const onFileChange = (imgURL) => {
    setFileURL(imgURL);
  };

  useEffect(() => {
    register('content', {
      required: true,
      validate: (value) => {
        if (value.replace(/<[^>]*>?/g, '').trim() === '') return false;
        if (value === '<p><br></p>') return false;
      },
    });
  }, [register]);

  useEffect(() => {
    if (props.data) {
      setValue('content', props.data.content);
      setValue('title', props.data.title);
      setValue('subtitle', props.data.subtitle);
      setValue('category', props.data.category);
    }
  }, []);

  useEffect(async () => {
    const data = await getBookList(19);
    setBooklist(data.data);
  }, []);

  const onChange = (editorContent) => {
    setValue('content', editorContent);
  };

  const onUpdateDiary = (data) => {
    const updateDiaryData = {
      title: data.title,
      category: Number(data.category),
      subtitle: data.subtitle,
      content: data.content,
      image: fileURL || props.data.diaryimage,
    };
    console.log('update', updateDiaryData);
  };

  const onWriteDiary = async (data) => {
    const diaryData = {
      title: data.title,
      content: data.content,
      category: Number(data.category),
      image: fileURL,
    };
    console.log(diaryData);
    // const res = await addDiary(diaryData);
    // console.log(res);
    console.log('write', diaryData);
  };

  const editorContent = watch('content');

  const onCancle = () => {
    setConfirm(!confirm);
  };

  const confirmCancle = (res) => {
    if (res) {
      onCancle();
      navigate(-1);
    } else {
      onCancle();
    }
  };

  return (
    <Container
      onSubmit={
        props.isEdit ? handleSubmit(onUpdateDiary) : handleSubmit(onWriteDiary)
      }
    >
      <Mid>
        <div>
          <input
            // wrap="on"
            className="title"
            type="text"
            placeholder="제목"
            {...register('title', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />

          <input
            // wrap="on"
            className="subtitle"
            id="subtitle"
            type="text"
            placeholder="소제목"
            {...register('subtitle', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />

          <br />
          <label className="book" htmlFor="book">
            일기장
          </label>
          {props.isEdit ? (
            <span className="booktitle">{props.booktitle || '나의하루'}</span>
          ) : (
            <select id="book" {...register('book')}>
              {booklist &&
                booklist.map((el, idx) => (
                  <option value={el.id} key={idx}>
                    {el.title}
                  </option>
                ))}
            </select>
          )}
          <br />
          <label className="category" htmlFor="category">
            주제
          </label>
          <select id="category" {...register('category')}>
            {['일상 공유', '공감과 치유', '문화 생활', '여행 기록', '자유'].map(
              (el, idx) => (
                <option value={idx} key={idx}>
                  {el}
                </option>
              )
            )}
          </select>
        </div>
        <ImageInput onFileChange={onFileChange} />
      </Mid>
      <Bottom>
        {props.isEdit && (
          <img
            className="previewImg"
            src={fileURL || props.data.diaryimage}
            alt="preview Img"
          />
        )}
        <TextEditor content={editorContent} onChange={onChange} />
        <div className="rightbox">
          <p className="Error">{errors && '모든 내용을 입력해 주세요'}</p>
          <BorderButton
            width="120px"
            height="40px"
            type="submit"
            fontSize={`${({ theme }) => theme.fontSize.fontSizeM}`}
            text={props.isEdit ? '수정하기' : '기록하기'}
          />
          <BorderButton
            text="취소"
            width="120px"
            height="40px"
            fontSize={`${({ theme }) => theme.fontSize.fontSizeM}`}
            onClick={() => {
              setConfirm(!confirm);
            }}
          />
        </div>
      </Bottom>
      {confirm && (
        <ConfirmModal
          message={'취소하시겠습니까?'}
          onComfirm={confirmCancle}
          target={'작성한 내용은 복구할 수 없습니다'}
        />
      )}
    </Container>
  );
};

export default WriteOrEditDiaryForm;

export const Container = styled.form`
  margin: 0 auto;
  width: 100%;
`;

export const Mid = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 0 ${({ theme }) => theme.space.spaceS};
  border-bottom: 1px solid ${({ theme }) => theme.colors.grey};

  & > div {
    width: 50%;
  }

  & input {
    padding: 0;
    margin-top: ${({ theme }) => theme.space.spaceM};
    overflow: visible;
    width: 100%;
    height: 100%;
    border: none;
    display: inline-block;
  }

  & select {
    width: 30%;
    border: none;
    background-color: ${({ theme }) => theme.colors.dimGrey};
    border-radius: ${({ theme }) => theme.borderRadius.borderRadiusS};
  }

  & input:focus-visible {
    outline: none;
  }

  .title {
    height: 50px;
    font-size: ${({ theme }) => theme.fontSize.fontSizeLL};
  }

  .title::placeholder {
    font-size: ${({ theme }) => theme.fontSize.fontSizeLL};
  }

  .subtitle {
    height: 50px;
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
  }

  .subtitle::placeholder {
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
  }

  label {
    margin-top: ${({ theme }) => theme.space.spaceM};
    width: 50px;
    display: inline-block;
  }

  .booktitle {
    font-size: ${({ theme }) => theme.fontSize.fontSizeM};
  }

  .radioBox {
    margin-top: ${({ theme }) => theme.space.spaceM};
    font-size: ${({ theme }) => theme.fontSize.fontSizeS};

    p {
      margin-bottom: ${({ theme }) => theme.space.spaceS};
    }

    input,
    label {
      cursor: pointer;
      margin: calc(${({ theme }) => theme.space.spaceS} / 3);
      display: inline;
    }

    input {
      width: 10px;
      display: inline;
    }
  }

  .addImgButton {
    cursor: pointer;
    margin: ${({ theme }) => theme.space.spaceM} 0
      ${({ theme }) => theme.space.spaceM} ${({ theme }) => theme.space.spaceS};
    width: 50%;
    height: 427.5px;
    overflow: hidden;
    border-radius: ${({ theme }) => theme.borderRadius.borderRadiusM};
    border: 1px dashed ${({ theme }) => theme.colors.dimGrey};
    ${({ theme }) => theme.layout.flexCenter}

    & > p {
      font-size: ${({ theme }) => theme.fontSize.fontSizeL};
      color: ${({ theme }) => theme.colors.text4};
    }

    &:hover {
      & > p {
        color: ${({ theme }) => theme.colors.text3};
      }
    }
  }

  .image {
    display: none;
  }

  @media ${({ theme }) => theme.screen.mobile} {
    align-items: center;
    flex-direction: column;
    & > div {
      width: 100%;
    }

    .addImgButton {
      margin: ${({ theme }) => theme.space.spaceM} 0;
      width: 100%;
    }
  }
`;

export const Bottom = styled.div`
  width: 100%;
  padding-bottom: ${({ theme }) => theme.space.spaceL};

  .preview {
    p {
      font-size: ${({ theme }) => theme.fontSize.fontSizeM};
      padding: ${({ theme }) => theme.space.spaceS}
        ${({ theme }) => theme.space.spaceS} 0
        ${({ theme }) => theme.space.spaceS};
    }
  }

  .previewImg {
    padding: ${({ theme }) => theme.space.spaceS};
    width: 100%;
  }

  .quill {
    border-top: 1px solid ${({ theme }) => theme.colors.grey};
    z-index: 2000;
    padding: ${({ theme }) => theme.space.spaceM};
    ${({ theme }) => theme.space.spaceS};
    color: ${({ theme }) => theme.colors.text2};
    min-height: 20rem;
  }

  .ql-editor {
    font-size: ${({ theme }) => theme.fontSize.fontSizeM};
    padding: 0px;
  }

  .rightbox {
    & > * {
      margin-right: ${({ theme }) => theme.space.spaceS};
    }

    & > button:last-child {
      margin: 0;
    }
    display: flex;
    justify-content: end;
    align-items: flex-end;
    margin: ${({ theme }) => theme.space.spaceM} 0;
  }

  .Error {
    font-size: calc(${({ theme }) => theme.fontSize.fontSizeS} * 1.2);
    color: ${({ theme }) => theme.colors.red};
    display: inline-block;
  }
`;
